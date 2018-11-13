val packageName = "scala-taggedtypes"
name := packageName


val scalaV = "2.12.7"

val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "com.chuusai" %% "shapeless" % "2.3.2",
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  ),
  scalaVersion := scalaV,
  organization := "es.tdev.taggedtypes",
  bintrayPackage := packageName,
  licenses ++= Seq(("MIT", url("http://opensource.org/licenses/MIT"))),
)

lazy val root = project.in(file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(publish := {})
  .aggregate(macros)

lazy val macros = project.in(file("annotations"))
  .settings(commonSettings)

enablePlugins(GitVersioning)

git.useGitDescribe := true

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))