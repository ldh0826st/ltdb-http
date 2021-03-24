import com.stlogic.ltdb.http.IngestModule
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

class Project extends IngestModule {

import IngestModule._

  class Projects(var project_id: Long, var title: String, var user_id: Long, var project_type: Long, var data_type: Long, var description: String, var version: String, var source: String, var file_count: Long, var thumbnail: String, var create_date: String, var update_date: String, var tags: String )


  case class Data (var projects: Array[Projects])

  override def parse(schema: StructType, data: String): Array[Row] = {
    val parsed: Data = GSON.fromJson(data, classOf[Data])
    parsed.projects.map(project => {
      toRow(schema, project)
    })
  }    
}

scala.reflect.classTag[Project].runtimeClass
