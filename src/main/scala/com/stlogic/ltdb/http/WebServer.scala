package com.stlogic.ltdb.http

import com.skt.spark.r2.util.Logging
import org.eclipse.jetty.io.MappedByteBufferPool
import org.eclipse.jetty.server._
import org.eclipse.jetty.server.handler.{HandlerCollection, RequestLogHandler}
import org.eclipse.jetty.servlet.{DefaultServlet, ServletContextHandler}
import org.eclipse.jetty.util.ssl.SslContextFactory

import java.io.File
import java.net.InetAddress
import javax.servlet.ServletContextListener
import scala.util.Try

class WebServer(ltdbServerConf: LTDBServerConf, var host: String, var ports: Array[Int]) extends Logging {
  val server = new Server()

  server.setStopTimeout(1000)
  server.setStopAtShutdown(true)

  val (connectors, protocol) = Option(ltdbServerConf.get(LTDBServerConf.SSL_KEYSTORE)) match {
    case None =>
      val outputBufferSize = ltdbServerConf.getInt(LTDBServerConf.OUTPUT_BUFFER_SIZE)

      val http = new HttpConfiguration()
      http.setOutputBufferSize(outputBufferSize)
      (ports.zipWithIndex.map(d => {
        val byteBufferPool = new MappedByteBufferPool(outputBufferSize)
        val connector = if (d._2 == 0)
          new ServerConnector(server, null, null, byteBufferPool, -1, -1, new HttpConnectionFactory(http))
        else
          new ServerConnector(server, null, null, byteBufferPool, 0, 1, new HttpConnectionFactory(http))
        connector.setHost(host)
        connector.setPort(d._1)
        connector.asInstanceOf[Connector]
      }), "http")

    case Some(keystore) =>
      val outputBufferSize = ltdbServerConf.getInt(LTDBServerConf.OUTPUT_BUFFER_SIZE)

      val https = new HttpConfiguration()
      https.setOutputBufferSize(outputBufferSize)
      https.addCustomizer(new SecureRequestCustomizer())

      val sslContextFactory = new SslContextFactory()
      sslContextFactory.setKeyStorePath(keystore)

      sslContextFactory.setKeyStorePassword(ltdbServerConf.get(LTDBServerConf.SSL_KEYSTORE_PASSWORD))
      sslContextFactory.setKeyManagerPassword(ltdbServerConf.get(LTDBServerConf.SSL_KEY_PASSWORD))

      (ports.zipWithIndex.map(d => {
        val byteBufferPool = new MappedByteBufferPool(outputBufferSize)
        val connector = if (d._2 == 0) new ServerConnector(server, null, null, byteBufferPool, -1, -1,
          new SslConnectionFactory(sslContextFactory, "http/1.1"),
          new HttpConnectionFactory(https))
        else new ServerConnector(server, null, null, byteBufferPool, 0, 1,
          new SslConnectionFactory(sslContextFactory, "http/1.1"),
          new HttpConnectionFactory(https))
        connector.setHost(host)
        connector.setPort(d._1)
        connector.asInstanceOf[Connector]
      }), "https")
  }

  server.setConnectors(connectors)

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
  requestLog.setLogTimeZone("GMT+9")
  requestLog.setRetainDays(ltdbServerConf.getInt(LTDBServerConf.REQUEST_LOG_RETAIN_DAYS))
  requestLog.setLogLatency(true)
  requestLogHandler.setRequestLog(requestLog)
  handlers.addHandler(requestLogHandler)

  server.setHandler(handlers)

  def addEventListener(listener: ServletContextListener): Unit = {
    context.addEventListener(listener)
  }

  def start(): Unit = {
    server.start()

    if (host == "0.0.0.0") {
      host = InetAddress.getLocalHost.getCanonicalHostName
    }
    val ports = server.getConnectors.map(connector => connector.asInstanceOf[NetworkConnector].getPort)

    logInfo("Starting server on %s://%s:%s" format(protocol, host, ports.mkString(",")))
  }

  def join(): Unit = {
    server.join()
  }

  def stop(): Unit = {
    context.stop()
    server.stop()
  }
}
