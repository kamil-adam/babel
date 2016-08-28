import sbt._
import Keys._

object BabelBuild extends Build {
  lazy val babel = Project(id = "babel",
    base = file(".")) aggregate (entities, daos)

  lazy val utils = Project(id = "babel-utils",
    base = file("utils"))

  lazy val entities = Project(id = "babel-entities",
    base = file("entities"))

  lazy val daos = Project(id = "babel-daos",
    base = file("daos"))

  lazy val composites = Project(id = "babel-composites",
    base = file("composites"))
}