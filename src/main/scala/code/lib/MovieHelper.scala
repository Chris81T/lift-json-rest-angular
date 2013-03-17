package code.lib

import code.model._

import net.liftweb._
import http._
import rest._
import json._
import util.Helpers
import util.Helpers._

/**
 * is used as json request value on client-side (angular ctrl)
 */
case class MoviesRequest(from: Int, to: Int)

object MovieHelper extends RestHelper {

  serve {
    case "movies" :: Nil JsonGet _ => MovieService.getMoviesAsJSON()

    /*
     This one is especially for a rest service a good choice. If from or to could not be converted as a number, a 404
     will be returned
     */
    case "movies" :: "from" :: AsInt(from) :: "to" :: AsInt(to) :: Nil JsonGet _ =>
      MovieService.getMoviesAsJSON(from, to)

    /*
     This was my first try. Please do not use this case in your code. It is no good style
     */
    case "movies" :: "badcase" :: "from" :: from :: "to" :: to :: Nil JsonGet _ =>
      val fromAsOpt = Helpers.asInt(from)
      val toAsOpt = Helpers.asInt(to)
      if (fromAsOpt.isDefined && toAsOpt.isDefined) {
        val fromAsInt = fromAsOpt.get
        val toAsInt = toAsOpt.get
        MovieService.getMoviesAsJSON(fromAsInt, toAsInt)
      } else {
        BadResponse()
      }

    /*
     Actually this case wont work! If you want to compile the code, please comment it out or remove it!
     */
//    case "movies" :: "forcomp" :: "from" :: from :: "to" :: to :: Nil JsonGet _ =>
//      val response = for {
//        fromAsInt <- Helpers.asInt(from)
//        toAsInt <- Helpers.asInt(to)
//      } yield MovieService.getMoviesAsJSON(fromAsInt, toAsInt)
//      response.getOrElse(JNull)

    case "movies" :: "forcomp2" :: "from" :: from :: "to" :: to :: Nil JsonGet _ =>
      for {
        fromAsInt <- Helpers.asInt(from) ?~ "*from* could not be converted to an integer!" ~> 400
        toAsInt <- Helpers.asInt(to) ?~ "*to* could not be converted to an integer!" ~> 400
      } yield MovieService.getMoviesAsJSON(fromAsInt, toAsInt)

  }

}

object MovieService {

  val loadSize = 5

  private implicit val format = DefaultFormats

  def getMoviesAsJSON(): JValue = getMoviesAsJSON(0, MovieBase.movies.length)

  def getMoviesAsJSON(from: Int, to: Int): JValue = {
    // instead of JArray(for (movie <- MovieBase.getMovies(from, to)) yield Extraction.decompose(movie))
    // simply:
    Extraction.decompose(MovieBase.getMovies(from, to))
  }

  def extractMoviesRequest(value: JValue): MoviesRequest = value.extract[MoviesRequest]


}