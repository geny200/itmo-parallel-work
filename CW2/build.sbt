name := "CW2"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "io.estatico"            %% "newtype"                    % "0.4.4",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
  "dev.zio"                %% "zio"                        % "2.0.0-M4",
  "dev.zio"                %% "zio-streams"                % "2.0.0-M4",
  "org.scalatest"          %% "scalatest"                  % "3.2.10" % Test
)
//addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
scalacOptions ++= Seq(
  "-Ymacro-annotations",
  "-Xmaxerrs",
  "200"
)
