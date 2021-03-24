import com.stlogic.ltdb.http.IngestModule
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

class Dataset extends IngestModule {

import IngestModule._


  class Datasets( var dataset_id: Long, var project_id: Long, var title: String, var thumbnail: String, var description: String, var version: String, var create_date: String, var update_date: String, var tags: String )

  case class Data (var datasets: Array[Datasets])

  override def parse(schema: StructType, data: String): Array[Row] = {
    val parsed: Data = GSON.fromJson(data, classOf[Data])
    parsed.datasets.map(dataset => {
      toRow(schema, dataset)
    })
  }    
}

scala.reflect.classTag[Dataset].runtimeClass
