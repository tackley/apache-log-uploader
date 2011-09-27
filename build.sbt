scalaVersion := "2.9.1"


libraryDependencies ++= Seq(
    "com.mongodb.casbah" % "casbah-core_2.9.0-1" % "2.1.5-1",
    "org.specs2" %% "specs2" % "1.6.1" % "test",
    "org.scala-tools.time" %% "time" % "0.5",
    "org.slf4j" % "slf4j-simple" % "1.6.2"
)

