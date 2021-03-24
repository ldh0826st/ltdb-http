package com.stlogic.ltdb.http

import com.stlogic.ltdb.common.Conf
import com.stlogic.ltdb.common.Conf.ConfEntry

import java.io.{File, InputStreamReader}
import java.lang.{Boolean => JBoolean, Long => JLong}
import java.net.URL
import java.nio.charset.StandardCharsets.UTF_8
import java.util.Properties
import scala.collection.JavaConverters.asScalaSetConverter

object LTDBServerConf {

  case class Entry(override val key: String, override val dflt: AnyRef) extends ConfEntry

  object Entry {
    def apply(key: String, dflt: Boolean): Entry = Entry(key, dflt: JBoolean)

    def apply(key: String, dflt: Int): Entry = Entry(key, dflt: Integer)

    def apply(key: String, dflt: Long): Entry = Entry(key, dflt: JLong)
  }

  val SERVER_HOST = Entry("ltdb.http.host", "0.0.0.0")
  val SERVER_PORT = Entry("ltdb.http.port", 8080)

  val REQUEST_LOG_RETAIN_DAYS = Entry("ltdb.http.request-log-retain.days", 5)

  val SSL_KEYSTORE = Entry("ltdb.http.keystore", null)
  val SSL_KEYSTORE_PASSWORD = Entry("ltdb.http.keystore.password", null)
  val SSL_KEY_PASSWORD = Entry("ltdb.http.key-password", null)
}

class LTDBServerConf(loadDefaults: Boolean) extends Conf[LTDBServerConf](null) {

  def this() = this(true)

  if (loadDefaults) {
    loadFromMap(sys.props)
  }

  def getPropertiesFromFile(file: File): Map[String, String] = {
    loadProperties(file.toURI().toURL())
  }

  def loadProperties(url: URL): Map[String, String] = {
    val inReader = new InputStreamReader(url.openStream(), UTF_8)
    try {
      val properties = new Properties()
      properties.load(inReader)
      properties.stringPropertyNames().asScala.map { k =>
        (k, properties.getProperty(k).trim())
      }.toMap
    } finally {
      inReader.close()
    }
  }

  def loadFromFile(name: String): LTDBServerConf = {
    getConfigFile(name)
      .map(getPropertiesFromFile)
      .foreach(loadFromMap)
    this
  }

  private val configDir: Option[File] = {
    val path = sys.env.get("LTDB_HTTP_CONF_DIR")
      .orElse(sys.env.get("LTDB_HTTP_HOME").map(path => s"$path${File.separator}conf"))
      .map(new File(_))
      .filter(_.exists())
    if (path.isEmpty) Option(new File("./conf")) else path
  }

  private def getConfigFile(name: String): Option[File] = {
    configDir.map(new File(_, name)).filter(_.exists())
  }

  private def loadFromMap(map: Iterable[(String, String)]): Unit = {
    map.foreach { case (k, v) =>
      if (k.startsWith("ltdb.")) {
        set(k, v)
      }
    }
  }

  def configToSeq(entry: LTDBServerConf.Entry): Seq[String] = {
    Option(get(entry)).map(_.split("[, ]+").toSeq).getOrElse(Nil)
  }
}