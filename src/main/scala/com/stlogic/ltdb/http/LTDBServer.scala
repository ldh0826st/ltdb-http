package com.stlogic.ltdb.http

import com.skt.spark.r2.util.Logging
import com.stlogic.omnisci.thrift.server.MapD
import org.apache.thrift.protocol.TJSONProtocol
import org.apache.thrift.server.TServlet
import org.eclipse.jetty.server.ResourceService
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.{DefaultServlet, FilterHolder, ServletHolder}
import org.eclipse.jetty.servlets.CrossOriginFilter
import org.eclipse.jetty.util.resource.Resource
import org.scalatra.metrics.MetricsBootstrap
import org.scalatra.servlet.ServletApiImplicits

import java.io.File
import java.util.EnumSet
import javax.servlet._
import scala.util.Try

class LTDBServer extends Logging {

  import LTDBServerConf._

  private var server: WebServer = _
  private[http] var ltdbServerConf: LTDBServerConf = _

  {
    val path = sys.env.get("LTDB_HTTP_LOGS_DIR")
      .orElse(sys.env.get("LTDB_HTTP_HOME").map(path => s"$path${File.separator}logs"))
      .map(new File(_))
      .filter(_.exists())
    System.setProperty("LTDB_HTTP_LOGS_DIR", path.getOrElse(new File("./logs")).getAbsolutePath)
  }

  def start(): Unit = {
    ltdbServerConf = new LTDBServerConf().loadFromFile("ltdb-server.conf")
    val host = ltdbServerConf.get(SERVER_HOST)
    val ports = ltdbServerConf.get(SERVER_PORTS)

    server = new WebServer(ltdbServerConf, host, ports.split(",").map(port => port.toInt))

    server.context.addEventListener(
      new ServletContextListener() with MetricsBootstrap with ServletApiImplicits {
        private def mount(sc: ServletContext, servlet: Servlet, mappings: String*): Unit = {
          val registration = sc.addServlet(servlet.getClass().getName(), servlet)
          registration.addMapping(mappings: _*)
        }

        override def contextDestroyed(sce: ServletContextEvent): Unit = {
        }

        override def contextInitialized(sce: ServletContextEvent): Unit = {
          val context = sce.getServletContext

          val thriftServlet = new TServlet(new MapD.Processor[ThriftHandler](new ThriftHandler(ltdbServerConf)), new TJSONProtocol.Factory)
          mount(context, thriftServlet, "/*")

          val ingestServlet = new IngestServlet(ltdbServerConf)
          mount(context, ingestServlet, "/ingest/*")

          val queryServlet = new QueryServlet(ltdbServerConf)
          mount(context, queryServlet, "/query/*")

          val pcaServlet = new PcaServlet(ltdbServerConf)
          mount(context, pcaServlet, "/pca/*")

          val occludedServlet = new OccludedServlet(ltdbServerConf)
          mount(context, occludedServlet, "/occluded/*")

          ltdbServerConf.get(LTDBServerConf.RESOURCE_DIRS).split(",").map(resourceName => {
            val resourceDir = Try(sys.env.getOrElse("LTDB_HTTP_HOME", ".")).getOrElse(".") + s"/${resourceName}"
            (resourceName, new File(resourceDir))
          }).foreach(resource => {
            if (resource._2.exists()) {
              val registration = context.addServlet(resource._1, classOf[DefaultServlet])
              registration.setInitParameter("resourceBase", Resource.newResource(resource._2).toString)
              registration.setInitParameter("dirAllowed", "true")
              registration.setInitParameter("pathInfoOnly", "true")
              registration.addMapping(s"/${resource._1}/*")
            }
          })
        }
      }
    )

    val holder = new FilterHolder
    holder.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*")
    holder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "POST,GET,OPTIONS,PUT,DELETE,HEAD")
    holder.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept")
    holder.setInitParameter(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, "1728000")
    holder.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true")
    holder.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, "false")
    val corsFilter = new CrossOriginFilter
    holder.setFilter(corsFilter)
    server.context.addFilter(holder, "/*", EnumSet.allOf(classOf[DispatcherType]))

    Runtime.getRuntime().addShutdownHook(new Thread("LTDB Server Shutdown") {
      override def run(): Unit = {
        logInfo("Shutting down ltdb server.")
        server.stop()
      }
    })

    server.start()

    SparkService.init(ltdbServerConf)
  }

  def join(): Unit = server.join()

  def stop(): Unit = {
    if (server != null) {
      server.stop()
    }
  }
}

object LTDBServer {
  def main(args: Array[String]): Unit = {
    val server = new LTDBServer()
    try {
      server.start()
      server.join()
    } finally {
      server.stop()
    }
  }
}
