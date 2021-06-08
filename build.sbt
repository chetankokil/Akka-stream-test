name := "akka-streams-http"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("com.test.akkastreams")

val AkkaVersion = "2.6.14"


libraryDependencies ++= Seq(
//  "com.typesafe" % "config" % "1.4.1",
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.1.0",
//  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
//  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test
)
