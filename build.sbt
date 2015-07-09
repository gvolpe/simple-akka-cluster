organization := "com.gvolpe"

name := """simple-akka-cluster"""

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

val akkaVersion= "2.3.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "org.fusesource" % "sigar" % "1.6.4",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)