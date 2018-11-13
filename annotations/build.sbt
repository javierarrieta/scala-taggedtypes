name := "annotations"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

libraryDependencies ++= Seq(
"org.scala-lang" % "scala-reflect" % scalaVersion.value,
)