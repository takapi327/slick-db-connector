import sbt._

name         := "slick-db-connector"
organization := "io.github.takapi327"

ThisBuild / organizationName := "Takahiko Tominaga"
ThisBuild / startYear        := Some(2021)

ThisBuild / scalaVersion := "2.13.3"

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

libraryDependencies ++= Seq(
  "mysql"               % "mysql-connector-java" % "5.1.48",
  "com.typesafe.slick" %% "slick"                % "3.3.3",
)
