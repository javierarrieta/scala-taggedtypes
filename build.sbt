import ProjectSettings._

val packageName = "scala-taggedtypes"

name := packageName

val scala212V = "2.12.7"
val scala211V = "2.11.12"

val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "com.chuusai" %% "shapeless" % "2.3.2",
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  ),
  scalaVersion := scala212V,
  crossScalaVersions := Seq(scala211V, scala212V),
  organization := "es.tdev.taggedtypes",
  bintrayPackage := packageName,
  licenses ++= Seq(("MIT", url("http://opensource.org/licenses/MIT"))),
  scalacOptions ++= (if (scalaVersion.value.startsWith("2.12.")) scala212CompilerSettings else scala211CompilerSettings),
  scalacOptions in (Compile, console) ~= (_.filterNot(Set("-Ywarn-unused:imports", "-Ywarn-unused-import", "-Ywarn-dead-code"))),
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