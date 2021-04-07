import com.stlogic.ltdb.http.IngestModule
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

class Instance extends IngestModule {

import IngestModule._



  class Instances( var instance_id: Long, var project_id: Long, var dataset_id: Long, var  file_id: Long, var cropped_image: String, var track_id: Long, var binding_id: Long, var class_name: String, var region_type: String, var region: String, var create_date: String, var update_date: String , var  feature_value: Array[Float], var feature_value_3d: String, var model_id: String, var extracting_method: String, var gender: String, var age: String, var expression: String, var action: String, var posture: String, var color: String, var cropped_image_path: String , var file_id_key: Long, var model_id_is_not_null: Boolean, var tags: String, var motion_score: Double, var confidence_score: Double )


  case class Data (var instances: Array[Instances])

  override def parse(schema: StructType, data: String): Array[Row] = {
    val parsed: Data = GSON.fromJson(data, classOf[Data])
    parsed.instances.map(instance => {
      instance.file_id_key = instance.file_id % 100
      instance.model_id_is_not_null = Option(instance.model_id).isDefined
      if(!Option(instance.feature_value).isDefined) {
        instance.feature_value = Array[Float]()
      }
      toRow(schema, instance)
    })
  }    
}

scala.reflect.classTag[Instance].runtimeClass
