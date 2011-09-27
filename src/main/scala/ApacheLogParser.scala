import com.mongodb.casbah.commons.MongoDBObject
import io.Source
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTime


case class LogEvent(dateTime: DateTime, url: String, method: String, response: Int, durationMilliSecs: Int) {
  lazy val path = url.takeWhile('?' !=)

  def asDbObject = MongoDBObject(
    "dt" -> dateTime,
    "url" -> url,
    "path" -> path,
    "method" -> method,
    "response" -> response,
    "ms" -> durationMilliSecs
  )
}

object ApacheLogParser {
  val LogRegex = """\S* \S* \S* \[(.*)\] "(\S*) (\S*) \S*" (\d*) \d* "\S*" ".*" (\d*)""".r
  val dateFormat = DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ss Z")

  def fromSource(s: Source) = s.getLines flatMap { buildLogEvent(_).toIterator }

  private def buildLogEvent(s: String) = s match {
    case LogRegex(dt, method, url, responseCode, durationMicrosecs) =>
      Some(LogEvent(dateFormat.parseDateTime(dt), url, method, responseCode.toInt, durationMicrosecs.toInt / 1000))
    case _ => None
  }
}
