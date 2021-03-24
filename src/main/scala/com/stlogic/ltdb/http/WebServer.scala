package com.stlogic.ltdb.http

import com.skt.spark.r2.util.Logging
import org.eclipse.jetty.server.handler.{HandlerCollection, RequestLogHandler}
import org.eclipse.jetty.server._
import org.eclipse.jetty.servlet.{DefaultServlet, ServletContextHandler}
import org.eclipse.jetty.util.ssl.SslContextFactory

import java.io.File
import java.net.InetAddress
import javax.servlet.ServletContextListener
import scala.util.Try

class WebServer(ltdbServerConf: LTDBServerConf, var host: String, var port: Int) extends Logging {
  val server = new Server()

  server.setStopTimeout(1000)
  server.setStopAtShutdown(true)

  val (connector, protocol) = Option(ltdbServerConf.get(LTDBServerConf.SSL_KEYSTORE)) match {
    case None =>
      val http = new HttpConfiguration()
      (new ServerConnector(server, new HttpConnectionFactory(http)), "http")

    case Some(keystore) =>
      val https = new HttpConfiguration()
      https.addCustomizer(new SecureRequestCustomizer())

      val sslContextFactory = new SslContextFactory()
      sslContextFactory.setKeyStorePath(keystore)

      sslContextFactory.setKeyStorePassword(ltdbServerConf.get(LTDBServerConf.SSL_KEYSTORE_PASSWORD))
      sslContextFactory.setKeyManagerPassword(ltdbServerConf.get(LTDBServerConf.SSL_KEY_PASSWORD))

      (new ServerConnector(server,
        new SslConnectionFactory(sslContextFactory, "http/1.1"),
        new HttpConnectionFactory(https)), "https")
  }

  connector.setHost(host)
  connector.setPort(port)

  server.setConnectors(Array(connector))

  val context = new ServletContextHandler()

  context.setContextPath("/")
  context.addServlet(classOf[DefaultServlet], "/")

  val handlers = new HandlerCollection
  handlers.addHandler(context)

  val requestLogHandler = new RequestLogHandler
  val requestLogDir = Try(sys.env.getOrElse("LTDB_HTTP_LOG_DIR",
    sys.env("LTDB_HTTP_HOME") + "/logs")).getOrElse("./logs")
  if (!new File(requestLogDir).exists()) {
    new File(requestLogDir).mkdirs()
  }
  val requestLog = new NCSARequestLog(requestLogDir + "/yyyy_mm_dd.request.log")
  requestLog.setAppend(true)
  requestLog.setExtended(false)
  requestLog.setLogTimeZone("GMT")
  requestLog.setRetainDays(ltdbServerConf.getInt(LTDBServerConf.REQUEST_LOG_RETAIN_DAYS))
  requestLogHandler.setRequestLog(requestLog)
  handlers.addHandler(requestLogHandler)

  server.setHandler(handlers)

  def addEventListener(listener: ServletContextListener): Unit = {
    context.addEventListener(listener)
  }

  def start(): Unit = {
    server.start()

    val connector = server.getConnectors()(0).asInstanceOf[NetworkConnector]

    if (host == "0.0.0.0") {
      host = InetAddress.getLocalHost.getCanonicalHostName
    }
    port = connector.getLocalPort

    logInfo("Starting server on %s://%s:%d" format(protocol, host, port))
  }

  def join(): Unit = {
    server.join()
  }

  def stop(): Unit = {
    context.stop()
    server.stop()
  }
}
