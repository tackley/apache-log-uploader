import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.MongoConnection
import io.Source
import com.mongodb.casbah.commons.Imports._

object Loader extends App {
  com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers()

  val coll = MongoConnection()("discussion-logs")("logs")

  if (args.isEmpty) {
    for {
      obj <- coll.find().sort(MongoDBObject("ms" -> -1)).limit(200)
      url <- Option(obj.get("url")).map(_.toString)
      ms <- Option(obj.get("ms")).map(_.toString.toInt)
    } {
      println("%-8d %s" format (ms, url))
    }

  } else {
    val srcFile = args(0)
    val host = args(1)


    println("Processing file %s with host %s..." format (srcFile, host))

    val src = Source.fromFile(srcFile, "UTF-8")

    for ((event, idx) <- ApacheLogParser.fromSource(src).zipWithIndex) {
      if (idx % 10000 == 0) println(idx)

      coll += (event.asDbObject ++ ("host" -> host))
    }
  }

}