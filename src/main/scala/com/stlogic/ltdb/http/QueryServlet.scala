package com.stlogic.ltdb.http

import com.google.gson.GsonBuilder
import com.skt.spark.r2.util.Logging
import com.stlogic.ltdb.http.QueryServlet.GSON
import org.scalatra._

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

  case class Query(sessionId: String, query: String, limit: Int = -1)

  post("/") {
    if (request.body.startsWith("{")) {
      val query = GSON.fromJson(request.body, classOf[Query])
      val limit = if (query.limit <= 0) {
        None
      } else {
        Option(query.limit)
      }
      Ok(GSON.toJson(SparkService.executeSql(query.sessionId, query.query, limit)))
    } else {
      Ok(GSON.toJson(SparkService.executeSql("0", request.body, None)))
    }
  }
}

object QueryServlet {
  val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create()
}