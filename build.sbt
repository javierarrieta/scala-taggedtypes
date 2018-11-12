name := "scala-taggedtypes"

organization := "jarrieta"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

enablePlugins(GitVersioning, BuildInfoPlugin)

git.useGitDescribe := true