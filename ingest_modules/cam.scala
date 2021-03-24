import com.stlogic.ltdb.http.IngestModule
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

class Cam extends IngestModule {

  import IngestModule._

  class Image(var identifier: String, var imsize: Array[Int])

  class Person(var ID: String, var BBox: Array[Int], var Head: Array[Int], var Keypoints: Array[Int])

  class Data(var image: Image, var persons: Array[Person])

  override def parse(schema: StructType, data: String): Array[Row] = {
    val parsed: Data = GSON.fromJson(data, classOf[Data])
    parsed.persons.map(person => {
      toRow(schema, person)
    })
  }
}

scala.reflect.classTag[Cam].runtimeClass