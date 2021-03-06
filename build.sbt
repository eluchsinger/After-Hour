name := """server-app"""

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.2.10.Final" // replace by your jpa implementation
)

// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "org.postgresql" % "postgresql" % "42.0.0"

resolvers += "jitpack" at "https://jitpack.io"

resolvers  += "Online Play Repository" at
  "http://repo.typesafe.com/typesafe/simple/maven-releases/"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)

//libraryDependencies += "com.github.eluchsinger" % "After-Hour-Shared" % "v0.1-alpha"