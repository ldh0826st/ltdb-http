package com.stlogic.ltdb.http

import com.google.gson.GsonBuilder
import com.skt.spark.r2.util.Logging
import com.stlogic.ltdb.http.QueryServlet.GSON
import org.scalatra._

import scala.collection.JavaConverters.mapAsJavaMapConverter
import scala.concurrent.ExecutionContext

class QueryServlet(ltdbServerConf: LTDBServerConf)
  extends ScalatraServlet with ApiFormats with FutureSupport with Logging {
  override protected implicit def executor: ExecutionContext = ExecutionContext.global

  SparkService.init(ltdbServerConf)

  override def shutdown(): Unit = {
  }

  error {
    case t: Throwable => {
      logError("error", t)
      InternalServerError(t.getStackTrace.mkString("\n"))
    }
  }

  post("/") {
    val query = request.body
    Ok(GSON.toJson(SparkService.executeSql(query)))
  }
}

object QueryServlet {
  val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create()
}