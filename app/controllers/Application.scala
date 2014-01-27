package controllers

import play.api._
import anorm._
import play.api.mvc._
import play.api.db._
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    DB withConnection { implicit c =>
      Ok(views.html.index(
        SQL("SELECT name, song, num FROM bad_songs ORDER BY num DESC")().map(row =>
          row[String]("name") :: row[String]("song") :: row[Int]("num") :: Nil
        ).toList
      ))
    }
  }

}