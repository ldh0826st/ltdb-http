import com.google.gson.GsonBuilder
import com.stlogic.ltdb.http.IngestModule
import org.apache.commons.io.IOUtils
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.scalatest.FlatSpec

import java.io.FileInputStream
import java.nio.charset.StandardCharsets.UTF_8
import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox

class IngestModuleSpec extends FlatSpec {
  val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues()
    .setExclusionStrategies()
    .create()

  "cam json data" should "parsed rows" in {
    val json = IOUtils.toString(new FileInputStream("./test/data/cam3_sample.json"), UTF_8)
    val code = IOUtils.toString(new FileInputStream("./ingest_modules/cam.scala"), UTF_8)
    val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()
    val clazz = tb.compile(tb.parse(code))().asInstanceOf[Class[_]]
    val ctor = clazz.getDeclaredConstructors()(0)
    val module = ctor.newInstance().asInstanceOf[IngestModule]
    val schema = StructType(Seq(
      StructField("ID", DataTypes.StringType),
      StructField("BBox", DataTypes.createArrayType(DataTypes.IntegerType)),
      StructField("Head", DataTypes.createArrayType(DataTypes.IntegerType)),
      StructField("Keypoints", DataTypes.createArrayType(DataTypes.IntegerType))
    ))
    module.parse(schema, json).foreach(row => {
      (0 to row.length - 1).map(i => {
        println(GSON.toJson(row))
      })
    })
    true
  }
}
