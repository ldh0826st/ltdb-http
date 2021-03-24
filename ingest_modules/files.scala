import com.stlogic.ltdb.http.IngestModule
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

class File extends IngestModule {

import IngestModule._

  class Files(var file_id: Long, var project_id: Long, var dataset_id: Long, var hash: String, var file_name: String, var file_path: String, var file_size: Long, var file_extension: String, var description: String, var thumbnail: String, var thumbnail_path: String, var frame_width: Long, var frame_height: Long, var create_date: String, var update_date: String, var duration: Double, var frame_rate: Double, var bit_rate: Double, var codec: String, var color_type: String, var is_ir: Boolean, var action: String, var place_class: String, var tags: String )


  case class FileData (var files: Array[Files])

  override def parse(schema: StructType, data: String): Array[Row] = {
    val parsed: FileData = GSON.fromJson(data, classOf[FileData])
    parsed.files.map(file => {
      toRow(schema, file)
    })
  }    
}

scala.reflect.classTag[File].runtimeClass
