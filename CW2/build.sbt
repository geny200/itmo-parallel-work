name := "itmo-parallel-cw-2-lab"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "io.estatico"            %% "newtype"                    % "0.4.4",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
  "org.scalatest"          %% "scalatest"                  % "3.2.10" % Test
)

scalacOptions ++= Seq(
  "-Ymacro-annotations",
  "-Xmaxerrs",
  "200"
)
