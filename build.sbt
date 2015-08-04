import AssemblyKeys._

name := "Holmes"

version := "1.0"

scalaVersion := "2.10.4"

fork := true

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.4.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.4.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "1.4.0" % "provided",
  "org.apache.spark" %% "spark-graphx" % "1.4.0" % "provided",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.4.0",
  "org.scalatest" % "scalatest_2.10" % "2.2.2" % "test",
  "com.typesafe" % "config" % "1.2.0",
  "net.debasishg" %% "redisclient" % "3.0",
  "com.databricks" % "spark-csv_2.10" % "1.1.0",
  "org.apache.httpcomponents" % "httpclient" % "4.4.1",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "org.apache.derby" % "derby" % "10.10.2.0",
  "org.hibernate" % "hibernate-core" % "4.3.10.Final",
  "org.hibernate" % "hibernate-ehcache" % "4.3.10.Final",
  "org.json4s" %% "json4s-jackson" % "3.2.11"
)

assemblySettings

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
  case "log4j.properties" => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}

test in assembly := {}
