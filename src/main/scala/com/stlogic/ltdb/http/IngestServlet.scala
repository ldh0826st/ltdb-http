package com.stlogic.ltdb.http

import com.google.common.cache.{CacheBuilder, CacheLoader, LoadingCache}
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.skt.spark.r2.RedisStoreType.FLASHBASE
import com.skt.spark.r2.common.Configuration
import com.skt.spark.r2.common.config.MapConfiguration
import com.skt.spark.r2.tjedis.FlashBaseStore
import com.skt.spark.r2.util.Logging
import com.skt.spark.r2.{RedisConfig, RedisStoreFactory, RedisTable, TableSchema}
import com.stlogic.ltdb.http.IngestServlet._
import org.apache.commons.io.FileUtils
import org.apache.commons.io.input.ReaderInputStream
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema
import org.apache.spark.sql.types._
import org.scalatra._

import java.io.{File, FilenameFilter, StringReader}
import java.nio.charset.StandardCharsets.UTF_8
import java.util
import scala.collection.JavaConverters.{asScalaBufferConverter, mapAsJavaMapConverter}
import scala.concurrent.ExecutionContext
import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox
import scala.util.Try

class IngestServlet(ltdbServerConf: LTDBServerConf)
  extends ScalatraServlet with ApiFormats with FutureSupport with Logging {
  override protected implicit def executor: ExecutionContext = ExecutionContext.global

  SparkService.init(ltdbServerConf)

  import IngestModule._

  override def shutdown(): Unit = {
  }

  error {
    case t: Throwable => {
      logError("error", t)
      InternalServerError(t.getStackTrace.mkString("\n"))
    }
  }

  get("/tables") {
    Ok(GSON.toJson(listTables()))
  }

  get("/tables/:name") {
    val name = params("name")
    Ok(GSON.toJson(getTableAsMap(name)))
  }

  get("/modules") {
    Ok(GSON.toJson(listModules()))
  }

  get("/modules/:name") {
    val name = params("name")
    Ok(getModule(name))
  }

  put("/modules/:name") {
    val name = params("name")
    val code = request.body
    val overwrite = Try(params("overwrite").toBoolean).getOrElse(false)
    writeModule(name, code, overwrite)
    Ok(true)
  }

  delete("/modules/:name") {
    val name = params("name")
    removeModule(name)
    Ok(true)
  }

  put("/:name/:table") {
    val name = params("name")
    val table = params("table")
    val data = request.body
    val module = moduleCache.get(name)
    val (redisTable, schema) = getTable(name)
    val parsed = module.parse(schema, data)
    storeCache.get(table).insertRows(redisTable, parsed.toIterator)
    Ok(true)
  }
}

object IngestServlet {

  val MAP_STRING_STRING_TYPE = new TypeToken[java.util.Map[String, String]]() {
  }.getType();
  val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create()

  private val storeCache: LoadingCache[String, FlashBaseStore] = CacheBuilder.newBuilder().maximumSize(10000).build(new StoreCacheLoader)

  class CustomRedisConfig(val config: Configuration) extends RedisConfig(new Broadcast[TableSchema](0) {
    override protected def getValue(): TableSchema = {
      TableSchema(null, config = config)
    }

    override protected def doUnpersist(blocking: Boolean): Unit = {
    }

    override protected def doDestroy(blocking: Boolean): Unit = {
    }
  }) {
  }

  class StoreCacheLoader() extends CacheLoader[String, FlashBaseStore] {
    override def load(key: String): FlashBaseStore = {
      val configuration = new MapConfiguration(SparkService.getTable(key)._2.asJava)
      val redisConfig = new CustomRedisConfig(configuration)
      RedisStoreFactory.obtainStoreFromConfig(FLASHBASE, redisConfig)
    }
  }

  def listTables(): Array[String] = {
    SparkService.getTables().asScala.toArray
  }

  def getTableAsMap(name: String): util.Map[String, Any] = {
    val table = SparkService.getTable(name)
    val options = table._2.asJava
    val schema = table._1
      .filter(f => !f.name.equalsIgnoreCase("geohash"))
      .foldLeft(Map.empty[String, String]) {
      case (m, e) => {
        val typeName = if (e.name.equalsIgnoreCase("geometry")) {
          "geometry"
        } else {
          e.dataType.typeName
        }
        m + (e.name -> typeName)
      }
    }.asJava
    Map[String, Any]("options" -> options, "schema" -> schema).asJava
  }

  def getTable(name: String): (RedisTable, StructType) = {
    val table = SparkService.getTable(name)
    val configuration = new MapConfiguration(table._2.asJava)
    (RedisTable.apply(table._1, configuration), table._1)
  }
}

abstract class IngestModule() {
  def parse(schema: StructType, data: String): Array[Row]
}

object IngestModule {
  val modulePath = Try(new File(sys.env.getOrElse("LTDB_HTTP_INGEST_MODULES_DIR",
    sys.env("LTDB_HTTP_HOME") + "/ingest_modules"))).getOrElse(new File("./ingest_modules"))
  val moduleCache: LoadingCache[String, IngestModule] = CacheBuilder.newBuilder().maximumSize(10000).build(new ModuleCacheLoader)

  class ModuleCacheLoader() extends CacheLoader[String, IngestModule] {
    override def load(key: String): IngestModule = {
      val code = FileUtils.readFileToString(new File(modulePath, key + ".scala"), UTF_8)
      val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()
      val clazz = tb.compile(tb.parse(code))().asInstanceOf[Class[_]]
      val ctor = clazz.getDeclaredConstructors()(0)
      ctor.newInstance().asInstanceOf[IngestModule]
    }
  }

  def existsModule(name: String): Boolean = {
    new File(modulePath, name + ".scala").exists()
  }

  def refreshModule(name: String): Unit = {
    moduleCache.invalidate(name)
  }

  def writeModule(name: String, code: String, overwrite: Boolean): Unit = {
    if (existsModule(name) && !overwrite) {
      throw new RuntimeException(s"$name is already exists")
    }
    FileUtils.copyInputStreamToFile(new ReaderInputStream(new StringReader(code)), new File(modulePath, name + ".scala"))
    refreshModule(name)
  }

  def removeModule(name: String): Unit = {
    new File(modulePath, name + ".scala").delete()
    refreshModule(name)
  }

  def getModule(name: String): String = {
    FileUtils.readFileToString(new File(modulePath, name + ".scala"), UTF_8)
  }

  def listModules(): Array[String] = {
    modulePath.listFiles(new FilenameFilter {
      override def accept(dir: File, name: String): Boolean = {
        name.endsWith(".scala")
      }
    }).map(f => {
      val name = f.getName
      name.substring(0, name.length - ".scala".length)
    })
  }

  val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create()

  def toRow(schema: StructType, obj: Any): Row = {
    var values = Array.fill[Any](schema.length)("")
    var contains = false
    obj.getClass.getDeclaredFields.foreach(f => {
      f.setAccessible(true)
      val name = f.getName
      if (schema.fieldNames.contains(name)) {
        val fieldIndex = schema.fieldIndex(name)
        values(fieldIndex) = f.get(obj) match {
          case null => ""
          case v: Array[_] => v.asInstanceOf[Array[_]].toSeq
          case v: Any => v
        }
        contains = true
      }
    })
    if (!contains) {
      throw new RuntimeException("At least one matched column is required in the schema.")
    }
    new GenericRowWithSchema(values, schema)
  }
}
