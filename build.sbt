name := "wall_of_shame"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.xerial" % "sqlite-jdbc" % "3.7.2"
)     

play.Project.playScalaSettings
