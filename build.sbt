name := "data-challenge"

organization := "com.virdis"

version := "0.0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "com.typesafe.play" % "play-json_2.11" % "2.5.0-M1"
)

initialCommands := "import com.virdis._"

