package controllers

import play.api._
import anorm._
import play.api.mvc._
import play.api.db._
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    DB withConnection { implicit c =>
      val songs = SQL("SELECT name, song, num FROM bad_songs ORDER BY num DESC")().map(row =>
        row[String]("name") :: row[String]("song") :: row[Int]("num") :: Nil
      ).toList
      val names = SQL("SELECT name, SUM(num) FROM bad_songs GROUP BY name ORDER BY SUM(num) DESC")().map(row =>
        row[String]("name") :: row[Int]("SUM(num)") :: Nil
      ).toList
      Ok(views.html.index(songs, names))
    }
  }

}