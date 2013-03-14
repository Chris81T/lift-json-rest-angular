package code.lib

import code.model._

import net.liftweb._
import http._
import rest._
import json._

/**
 * is used as json request value on client-side (angular ctrl)
 */
case class MoviesRequest(from: Int, to: Int)

object MovieHelper extends RestHelper {

  serve {
    case "movies" :: Nil JsonGet _ => MovieService.getMoviesAsJSON()
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