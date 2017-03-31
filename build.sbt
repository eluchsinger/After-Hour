name := """server-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

resolvers += "jitpack" at "https://jitpack.io"
resolvers += Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)
libraryDependencies += "com.github.eluchsinger" % "After-Hour-Shared" % "-SNAPSHOT"

fork in run := true

fork in run := true

fork in run := true