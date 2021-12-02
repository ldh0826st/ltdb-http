package com.stlogic.ltdb.http

import com.google.gson.GsonBuilder
import com.skt.spark.r2.util.Logging
import com.stlogic.fbgis.vector_tile.VectorTileBuilder
import com.stlogic.ltdb.http.QueryServlet.GSON
import org.scalatra._

import scala.concurrent.ExecutionContext

class QueryServlet(ltdbServerConf: LTDBServerConf)
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

  case class RenderQuery(queries: Array[String], typeName: String, geomName: String, zoom: Int, tx: Int, ty: Int, aggrType: String, multiple: Boolean, valueFilter: String)

  post("/render_sql") {
    val renderQuery = GSON.fromJson(request.body, classOf[RenderQuery])
    val queries = renderQuery.queries
    val typeName = renderQuery.typeName
    val geomName = if (renderQuery.geomName != null) renderQuery.geomName else "geometry"
    val zoom = renderQuery.zoom
    val tx = renderQuery.tx
    val ty = renderQuery.ty
    val aggrType = if (renderQuery.aggrType != null)
      VectorTileBuilder.AggregateType.valueOf(renderQuery.aggrType.toUpperCase())
    else
      VectorTileBuilder.AggregateType.SUM
    val multiple = renderQuery.multiple
    val valueFilter = if (renderQuery.valueFilter != null) Option[Double](renderQuery.valueFilter.toDouble) else None

    val result = SparkService.renderSql(queries, "", typeName, geomName, zoom, tx, ty, aggrType, multiple, valueFilter)

    response.setHeader("Content-Encoding", "gzip")
    response.setContentType("application/x-protobuf")
    response.setContentLength(result.image.array().length)
    Ok(result.image.array())
  }

  get("/render_sql") {
    val queries = if (params.contains("query")) {
      Array(params("query"))
    } else {
      Array(params("query1"), params("query2"))
    }
    val typeName = params("typeName")
    val geomName = params.getOrElse("geomName", "geometry")
    val zoom = params("zoom").toInt
    val tx = params("tx").toInt
    val ty = params("ty").toInt
    val aggrType = if (params.contains("aggrType")) {
      VectorTileBuilder.AggregateType.valueOf(params("aggrType").toUpperCase)
    } else
      VectorTileBuilder.AggregateType.SUM
    val multiple = params.getOrElse("multiple", "false").toBoolean
    val valueFilter = if (params.contains("valueFilter")) Option[Double](params("valueFilter").toDouble) else None

    val result = SparkService.renderSql(queries, "", typeName, geomName, zoom, tx, ty, aggrType, multiple, valueFilter)

    response.setHeader("Content-Encoding", "gzip")
    response.setContentType("application/x-protobuf")
    response.setContentLength(result.image.array().length)
    Ok(result.image.array())
  }

  // http://fbg01:4762/query/clear_render_cache
  get("/clear_render_cache") {
    Ok(SparkService.clearRenderCache())
  }
}

object QueryServlet {
  val GSON = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create()
}