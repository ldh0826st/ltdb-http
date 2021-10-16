package com.stlogic.ltdb.http

import com.google.common.base.Stopwatch
import com.google.common.cache.{CacheBuilder, CacheLoader, LoadingCache}
import com.google.common.collect.Lists
import com.google.common.io.BaseEncoding
import com.google.gson.GsonBuilder
import com.skt.spark.r2.kaetlyn.consumer.common.SchemaNotDefinedException
import com.skt.spark.r2.util.Logging
import com.stlogic.ltdb.common.VectorTileBuilder
import com.stlogic.omnisci.thrift.server._
import com.vividsolutions.jts.geom.Geometry
import org.apache.hadoop.util.Shell
import org.apache.spark.ml.feature.{PCA, StandardScaler}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.catalyst.TableIdentifier
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.r2.UDF.R2UDFs
import org.apache.spark.sql.r2.UDT.{GeometryUDTPublic, PointUDT}
import org.apache.spark.sql.types._

import java.io.File
import java.nio.ByteBuffer
import java.util
import java.util.Date
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.{Lock, ReentrantLock}
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Try

object SparkService extends Logging {
  val lock: Lock = new ReentrantLock()
  private var sparkSession: SparkSession = _
  private var initialized: Boolean = _
  private val tableCache: LoadingCache[String, (StructType, Map[String, String])] =
    CacheBuilder.newBuilder().maximumSize(10000).expireAfterAccess(5 * 60, TimeUnit.SECONDS).build(new TableCacheLoader)
  private val iteratorCache: LoadingCache[String, (StructType, Array[String], util.Iterator[Row], Array[Long])] =
    CacheBuilder.newBuilder().expireAfterAccess(5 * 60, TimeUnit.SECONDS).build(new IteratorCacheLoader)

  class TableCacheLoader() extends CacheLoader[String, (StructType, Map[String, String])] {
    override def load(tableName: String): (StructType, Map[String, String]) = {
      val dotIndex = tableName.indexOf('.')
      val database = if (dotIndex == -1) None else Some(tableName.substring(0, dotIndex))
      val table = if (dotIndex == -1) tableName else tableName.substring(dotIndex + 1)
      val tryGetMeta = Try(sparkSession.sessionState
        .catalog
        .getTableMetadata(TableIdentifier(table, database))
      )
      if (tryGetMeta.isFailure) {
        throw new SchemaNotDefinedException(tableName)
      }
      val catalogTbl = tryGetMeta.get
      val storageProperties: Map[String, String] = catalogTbl.storage.properties
      (catalogTbl.schema, storageProperties)
    }
  }

  class IteratorCacheLoader() extends CacheLoader[String, (StructType, Array[String], util.Iterator[Row], Array[Long])] {
    override def load(idAndQuery: String): (StructType, Array[String], util.Iterator[Row], Array[Long]) = {
      val query = idAndQuery.substring(idAndQuery.indexOf(",") + 1)
      val dataset = sparkSession.sql(query)
      val columnNames = dataset.columns
      val structType: StructType = dataset.schema
      (structType, columnNames, dataset.toLocalIterator(), Array[Long](0L))
    }
  }

  def init(ltdbServerConf: LTDBServerConf): Unit = {
    if (!initialized) {
      lock.lock()
      try {
        val master = ltdbServerConf.get("ltdb.spark.master")
        val configs = ltdbServerConf.iterator().asScala.filter(e => {
          !e.getKey.equals("ltdb.spark.master") && e.getKey.startsWith("ltdb.spark.")
        }).foldLeft(Map.empty[String, String]) {
          case (m, e) => {
            m + (e.getKey.substring("ltdb.".length) -> e.getValue)
          }
        }
        logInfo(s"spark configs: ${configs}")

        def create(master: String, configs: Map[String, String]): SparkSession = {
          System.setProperty("HADOOP_USER_NAME", configs.getOrElse("spark.hadoopUserName", "yarn"))

          val builder = SparkSession.builder
          builder.appName("ltdb-spark-service")
          builder.master(master)
          val isLocal = master.startsWith("local")
          val homeDir = Try(new File(sys.env("LTDB_HTTP_HOME"))).getOrElse(new File("./"))

          val sparkWarehouseDir = homeDir.toURI.toString + "/spark-warehouse"
          val hiveWarehouseDir = homeDir.toURI.toString + "/hive-warehouse"
          val scratchDir = homeDir.toURI.toString + "/tmp/hive"
          builder.config("spark.sql.warehouse.dir", sparkWarehouseDir)
          builder.config("hive.metastore.warehouse.dir", hiveWarehouseDir)
          builder.config("spark.hadoop.hive.exec.scratchdir", scratchDir)

          if (System.getProperty("os.name").startsWith("Windows")) {
            val tmp = new File(homeDir, "tmp/hive");
            tmp.mkdirs()
            System.setProperty("hadoop.home.dir", homeDir.getAbsolutePath)
            Shell.execCommand(null, Shell.getSetPermissionCommand("777", true, tmp.getAbsolutePath), 0L)
          }
          if (!isLocal) {
            configs.foreach(config => builder.config(config._1, config._2))
          }
          builder.enableHiveSupport()
          builder.getOrCreate
        }

        sparkSession = create(master, configs)
        R2UDFs.register()
        initialized = true
      } finally {
        lock.unlock()
      }
    }
  }

  def getTables(): util.List[String] = {
    sparkSession.sessionState.catalog.listTables("default").map(t => {
      t.table
    }).asJava
  }

  def getTable(tableName: String): (StructType, Map[String, String]) = {
    tableCache.get(tableName)
  }

  def getTableDetail(tableName: String): TTableDetails = {
    val (schema, _) = getTable(tableName)
    var count: Long = 0
    val columnTypes: util.List[TColumnType] = schema.fields.filter(f => !f.name.equalsIgnoreCase("geohash"))
      .map(f => {
        val typeInfo: TTypeInfo = new TTypeInfo( //
          getDatumType(f.dataType), // type
          TEncodingType.NONE, // encoding
          true, // nullable
          false, // is_array
          0, // precision
          0, // scale
          0 // comp_param
        )
        count += 1
        new TColumnType( //
          f.name, // col_name
          typeInfo, // col_type
          false, // is_reserved_keyword
          null, // src_name
          false, // is_system
          true, // is_physical
          count // col_id
        )
      }).toList.asJava
    new TTableDetails( //
      columnTypes, // row_desc
      32000000L, // fragment_size
      2097152L, // page_size
      4611686018427388000L, // max_rows
      "", // view_sql
      0, // shard_count
      "[]", // key_metainfo
      false, // is_temporary
      TPartitionDetail.DEFAULT // partition_detail
    )
  }

  def getDatumType(dataType: DataType): TDatumType = dataType match {
    case StringType => TDatumType.STR
    case ShortType => TDatumType.SMALLINT
    case IntegerType => TDatumType.INT
    case LongType => TDatumType.BIGINT
    case FloatType => TDatumType.FLOAT
    case DoubleType => TDatumType.DOUBLE
    case BooleanType => TDatumType.BOOL
    case DateType => TDatumType.TIMESTAMP
    case TimestampType => TDatumType.TIMESTAMP
    case PointUDT => TDatumType.POINT
    case GeometryUDTPublic => TDatumType.GEOMETRY
    case BinaryType => TDatumType.STR
    case ArrayType(_, _) => TDatumType.STR
    case _ => throw new RuntimeException("Unsupported type: " + dataType.typeName)
  }

  def executeSql(sessionId: String, query: String, column_format: Boolean, limit: Option[Int]): TQueryResult = {
    val stopwatch = Stopwatch.createStarted()
    val (structType: StructType, columnNames: Array[String], iterator: util.Iterator[Row], nonce: Long) = if (limit.isEmpty) {
      val dataset = sparkSession.sql(query)
      val columnNames = dataset.columns
      val structType: StructType = dataset.schema
      (structType, columnNames, dataset.toLocalIterator(), 1L)
    } else {
      val cached = iteratorCache.get(sessionId + "," + query)
      cached._4(0) = cached._4(0) + 1L
      (cached._1, cached._2, cached._3, cached._4(0))
    }

    val columnTypes: util.List[TColumnType] = Lists.newArrayListWithCapacity(columnNames.length)
    val datumTypes: Array[TDatumType] = new Array[TDatumType](columnNames.length)
    val rows: util.List[TRow] = Lists.newArrayList()
    val columns: util.List[TColumn] = Lists.newArrayList()

    for (i <- 0 until columnNames.length) {
      val columnName: String = columnNames(i)
      val dataType: DataType = structType.apply(columnName).dataType
      val datumType: TDatumType = getDatumType(dataType)
      var precision: Int = 0
      var scale: Int = 0
      if (TDatumType.BIGINT == datumType) precision = 38
      else if (TDatumType.FLOAT == datumType) {
        precision = 38
        scale = 8
      }
      else if (TDatumType.DOUBLE == datumType) {
        precision = 308
        scale = 16
      }
      val typeInfo: TTypeInfo = new TTypeInfo( //
        datumType, // type
        TEncodingType.NONE, // encoding
        true, // nullable
        false, // is_array
        precision, // precision
        scale, // scale
        -1 // comp_param
      )
      val columnType: TColumnType = new TColumnType(columnName, // col_name
        typeInfo, // col_type
        false, // is_reserved_keyword
        "", // src_name
        false, // is_system
        false, // is_physical
        i // col_id
      )
      columnTypes.add(columnType)
      datumTypes(i) = datumType
      if (column_format) {
        columns.add(new TColumn(
          new TColumnData(Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList()), Lists.newArrayList()))
      }
    }

    if (!column_format) {
      var count: Long = 0;
      var done = false;
      while (iterator.hasNext && !done) {
        try {
          val row = iterator.next()
          val datums: util.List[TDatum] = Lists.newArrayList()
          for (i <- 0 until columnNames.length) {
            val value: Any = row.get(i)
            val datum = new TDatum()
            datum.setIs_null(value == null)
            if (value != null) {
              structType.fields(i).dataType match {
                case StringType => datum.setVal(new TDatumVal().setStr_val(String.valueOf(value)))
                case ShortType | IntegerType | LongType => datum.setVal(new TDatumVal().setInt_val(value.asInstanceOf[Number].longValue))
                case FloatType | DoubleType => datum.setVal(new TDatumVal().setReal_val(value.asInstanceOf[Number].doubleValue))
                case BooleanType => datum.setVal(new TDatumVal().setInt_val(if (value.asInstanceOf[Boolean]) 1L else 0L))
                case DateType | TimestampType => datum.setVal(new TDatumVal().setInt_val(value.asInstanceOf[Date].getTime))
                case GeometryUDTPublic | PointUDT => datum.setVal(new TDatumVal().setStr_val(value.asInstanceOf[Geometry].toText))
                case BinaryType => datum.setVal(new TDatumVal().setStr_val(BaseEncoding.base64().encode(value.asInstanceOf[Array[Byte]])))
                case ArrayType(_, _) => datum.setVal(new TDatumVal().setStr_val(value.asInstanceOf[mutable.WrappedArray[_]].mkString(",")))
                case _ => throw new RuntimeException("Unsupported type: " + structType.fields(i).dataType.typeName)
              }
            }
            datums.add(datum)
          }
          rows.add(new TRow(datums))
        } finally {
          count += 1;
          if (limit.nonEmpty && count >= limit.get) {
            done = true
          }
        }
      }
    } else {
      var count: Long = 0;
      var done = false;
      while (iterator.hasNext && !done) {
        try {
          val row = iterator.next()
          for (i <- 0 until columnNames.length) {
            val column: TColumn = columns.get(i)
            val value: Any = row.get(i)
            column.getNulls.add(value == null)
            if (value != null)
              structType.fields(i).dataType match {
                case StringType => column.getData.getStr_col.add(String.valueOf(value))
                case ShortType | IntegerType | LongType => column.getData.getInt_col.add(value.asInstanceOf[Number].longValue)
                case FloatType | DoubleType => column.getData.getReal_col.add(value.asInstanceOf[Number].doubleValue)
                case BooleanType => column.getData.getInt_col.add(if (value.asInstanceOf[Boolean]) 1L else 0L)
                case DateType | TimestampType => column.getData.getInt_col.add(value.asInstanceOf[Date].getTime)
                case GeometryUDTPublic | PointUDT => column.getData.getStr_col.add(value.asInstanceOf[Geometry].toText)
                case BinaryType => column.getData.getStr_col.add(BaseEncoding.base64().encode(value.asInstanceOf[Array[Byte]]))
                case ArrayType(_, _) => column.getData.getStr_col.add(value.asInstanceOf[mutable.WrappedArray[_]].mkString(","))
                case _ => throw new RuntimeException("Unsupported type: " + structType.fields(i).dataType.typeName)
              }
          }
        } finally {
          count += 1;
          if (limit.nonEmpty && count >= limit.get) {
            done = true
          }
        }
      }
    }
    val hasNext = iterator.hasNext
    if (limit.nonEmpty && !hasNext) {
      iteratorCache.invalidate(sessionId + "," + query)
    }
    val rowSet: TRowSet = new TRowSet(columnTypes, // row_desc
      rows, // rows,
      columns, // columns
      column_format // is_columnar
    )
    val time: Long = stopwatch.elapsed(TimeUnit.MILLISECONDS)
    new TQueryResult(rowSet, // row_set
      time, // execution_time_ms
      time, // total_time_ms
      nonce.toString + "," + hasNext.toString // nonce
    )
  }

  def executeSql(sessionId: String, query: String, limit: Option[Int]): util.Map[String, Any] = {
    val stopwatch = Stopwatch.createStarted()
    val (structType: StructType, columnNames: Array[String], iterator: util.Iterator[Row], nonce: Long) = if (limit.isEmpty) {
      val dataset = sparkSession.sql(query)
      val columnNames = dataset.columns
      val structType: StructType = dataset.schema
      (structType, columnNames, dataset.toLocalIterator(), 1L)
    } else {
      val cached = iteratorCache.get(sessionId + "," + query)
      cached._4(0) = cached._4(0) + 1L
      (cached._1, cached._2, cached._3, cached._4(0))
    }

    var count: Long = 0;
    var done = false;
    val rowSet: util.List[util.Map[String, Any]] = Lists.newArrayList()
    while (iterator.hasNext && !done) {
      try {
        val row = iterator.next()
        val map = (0 until columnNames.length).map(index => {
          val name = columnNames(index)
          val value = if (row.get(index) != null) {
            structType.fields(index).dataType match {
              case StringType => row.getAs[String](index)
              case ShortType | IntegerType | LongType => row.getAs[Long](index)
              case FloatType | DoubleType => row.getAs[Double](index)
              case BooleanType => row.getAs[Boolean](index)
              case DateType | TimestampType => row.getAs[Date](index)
              case GeometryUDTPublic | PointUDT => row.getAs[Geometry](index).toText
              case BinaryType => BaseEncoding.base64().encode(row.getAs[Array[Byte]](index))
              case ArrayType(_, _) => row.getAs[mutable.WrappedArray[_]](index).toArray
              case _ => throw new RuntimeException("Unsupported type: " + structType.fields(index).dataType.typeName)
            }
          } else {
            null
          }
          (name -> value)
        }).toMap[String, Any].asJava
        rowSet.add(map)
      } finally {
        count += 1;
        if (limit.nonEmpty && count >= limit.get) {
          done = true
        }
      }
    }
    val hasNext = iterator.hasNext
    if (limit.nonEmpty && !hasNext) {
      iteratorCache.invalidate(sessionId + "," + query)
    }
    Map("execution_time_ms" -> stopwatch.elapsed(TimeUnit.MILLISECONDS), "rowset" -> rowSet, "nonce" -> (nonce.toString + "," + hasNext.toString)).asJava
  }

  def renderSql(query: String, nonce: String, typeName: String, zoom: Int, tx: Int, ty: Int,
                aggrType: com.stlogic.fbgis.vector_tile.VectorTileBuilder.AggregateType): TRenderResult = {
    val stopwatch = Stopwatch.createStarted()
    val dataset = sparkSession.sql(
      if (query.toUpperCase.contains(" WHERE ")) {
        query + s" AND ST_VectorTileAggr(geometry, '${zoom},${tx},${ty}', '${aggrType.name()}')"
      } else {
        query + s" WHERE ST_VectorTileAggr(geometry, '${zoom},${tx},${ty}', '${aggrType.name()}')"
      })
    val structType: StructType = dataset.schema

    val bytes = VectorTileBuilder.build(structType, dataset.rdd, typeName, aggrType)
    val time: Long = stopwatch.elapsed(TimeUnit.MILLISECONDS)
    new TRenderResult(
      ByteBuffer.wrap(bytes), // image
      nonce, // nonce
      time, // execution_time_ms
      time, // render_time_ms
      time, // total_time_ms
      "" // vega_metadata
    )
  }

  def executePca(query: String): String= {
    val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create()
    val stopwatch = Stopwatch.createStarted()
    val spark:SparkSession = sparkSession
    import spark.implicits._

    // process query
    var query_result= spark.sql(query)

    // convert float array to Vectors
    val convertToVector = udf((array: Seq[Float]) => {
      Vectors.dense(array.map(_.toDouble).toArray)
    })
    val vectorDf = query_result.withColumn("feature_value_vector", convertToVector($"feature_value"))

    // do StandardScaler
    val scaler = new StandardScaler()
      .setInputCol("feature_value_vector")
      .setOutputCol("feature_value_scale")
      .setWithStd(true)
      .setWithMean(true)
    val scalerModel = scaler.fit(vectorDf)
    val scaledDf = scalerModel.transform(vectorDf).select("project_id", "instance_id", "feature_value_scale", "cropped_image_path")

    // do PCA
    val count = scaledDf.count()
    if (count > 0) {
      val pca = new PCA()
        .setInputCol("feature_value_scale")
        .setOutputCol("feature_value_3d")
        .setK(3)
        .fit(scaledDf)
      val result = pca.transform(scaledDf).select("project_id", "instance_id", "feature_value_3d", "cropped_image_path")
      val time: Long = stopwatch.elapsed(TimeUnit.MILLISECONDS)
      val output = "{\"execution_time_ms\":" + time + ",\"rowset\":" + result.toJSON.collectAsList() + "}"

      return output
    } else {
      val time: Long = stopwatch.elapsed(TimeUnit.MILLISECONDS)
      val output = "{No result}"
      return output
    }
  }

  def executeOccluded(query: String): String= {
    val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create()
    val stopwatch = Stopwatch.createStarted()
    val spark:SparkSession = sparkSession
    import spark.implicits._

    // process query
    val df = spark.sql(query)
    var resultDf = Seq.empty[(Long,Boolean)].toDF("file_id", "Occluded")
    var tmpDf = spark.emptyDataFrame
    var df2 = spark.emptyDataFrame
    var dfResult = spark.emptyDataFrame

    val fileArray = df.select(df("file_id")).distinct.rdd.map(x=>x.mkString).collect

    if (fileArray.length > 0) {
      df.createOrReplaceTempView("occluded")
      var it_file = 0
      var occluded = false
      while (it_file < fileArray.length) {
        val fileDf = df.filter(df("file_id") === fileArray(it_file))
        fileDf.createOrReplaceTempView("occluded")

        val idArray = fileDf.selectExpr("instance_id").rdd.map(x=>x.mkString).collect
        val regionArray = fileDf.selectExpr("region").rdd.map(x=>x.mkString).collect

        var iterator = 0
        occluded = false
        while (occluded == false &&  iterator < idArray.length) {
          df2 = spark.sql("select instance_id from occluded  where file_id = " + fileArray(it_file) + " and instance_id > " + idArray(iterator)  + " and " + "ST_Overlaps(st_geomFromWKT(region ),st_geomFromWKT('" + regionArray(iterator) + "')) = true")

          if (df2.count() > 0) occluded = true
          iterator = iterator + 1
        }

        tmpDf = Seq((fileArray(it_file), occluded)).toDF("file_id", "Occluded")
        resultDf = resultDf.union(tmpDf)
        it_file = it_file + 1
      }

      val time: Long = stopwatch.elapsed(TimeUnit.MILLISECONDS)
      val output = "{\"execution_time_ms\":" + time + ",\"rowset\":" + resultDf.toJSON.collectAsList() + "}"
      return output
    } else {
      val time: Long = stopwatch.elapsed(TimeUnit.MILLISECONDS)
      val output = "{No result}"
      return output
    }
  }
}
