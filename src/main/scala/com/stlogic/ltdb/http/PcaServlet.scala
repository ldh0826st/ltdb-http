package com.stlogic.ltdb.http

import com.skt.spark.r2.util.Logging
import org.scalatra._

import scala.concurrent.ExecutionContext

class PcaServlet(ltdbServerConf: LTDBServerConf)
  extends ScalatraServlet with ApiFormats with FutureSupport with Logging {
  override protected implicit def executor: ExecutionContext = ExecutionContext.global

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
    Ok(SparkService.executePca(query))
  }
}


