package controllers

import play.api._
import anorm._
import play.api.mvc._
import play.api.db._
import play.api.Play.current

object Application extends Controller {

  def gifs(name: String) = Action {
    name match {
      case "shaq" => Ok(views.html.shaq("http://i.imgur.com/MngJhBl.gif"))
      case "cat" => Ok(views.html.shaq("http://i.imgur.com/aHVZR4R.gif"))
      case _ => NotFound(views.html.notfound())
    }
  }

  def index = Action {
    DB withConnection { implicit c =>
      val songs = SQL("SELECT name, song, num FROM bad_songs ORDER BY num DESC LIMIT 25")().map(row =>
        row[String]("name") :: row[String]("song") :: row[Int]("num") :: Nil
      ).toList
      val names = SQL("SELECT name, SUM(num) FROM bad_songs GROUP BY name ORDER BY SUM(num) DESC")().map(row =>
        row[String]("name") :: row[Int]("SUM(num)") :: Nil
      ).toList
      val recent = SQL("SELECT name, song, num FROM bad_songs ORDER BY modified DESC LIMIT 10")().map(row =>
        row[String]("name") :: row[String]("song") :: row[Int]("num") :: Nil
      ).toList
      Ok(views.html.wallofshame(songs, names, recent))
    }
  }

}
