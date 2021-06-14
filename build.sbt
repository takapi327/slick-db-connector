import sbt._

name         := "slick-db-connector"
organization := ""

ThisBuild / scalaVersion := "3.0.0"

version := "1.0.0"

lazy val root = (project in file("."))

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Ywarn-dead-code",
  "-Ymacro-annotations"
)
