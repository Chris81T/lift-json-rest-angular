package code.snippet

import net.liftweb._
import http._
import js.{JsCmds, JsCmd}
import js.JE._
import JsCmds._
import util.Helpers._

import json._


import code.model._
import code.lib._

/**
 * instead of object, this time the snippet should be a class to know the current
 * size of shown movies on the page
 */
class MovieSnippet {

  private var currentSize = 0

  def angular = {
    implicit val format = DefaultFormats


    def moviesRequest(request: JValue): JsCmd = {
      val fromTo = MovieService.extractMoviesRequest(request)
      val movies = MovieService.getMoviesAsJSON(fromTo.from, fromTo.to);
      Call("appendMovies", movies)
    }

    // greate json array of movies (of the first five movies)
    val movies = MovieService.getMoviesAsJSON(0, MovieService.loadSize);
    S.appendJs(Call("setupMovieCtrl", movies))
    "*" #> Script(
      Function("moviesRequest", "fromTo" :: Nil,
        SHtml.jsonCall(
          JsVar("fromTo"),
          moviesRequest _
        )._2.cmd
      )
    )
  }

  def onlyLift = {

    def partialLoad(): JsCmd = {
      println("partial load on lift side. current-size = " + currentSize)
      val movies = MovieBase.getMovies(currentSize, currentSize + MovieService.loadSize)
      currentSize = currentSize + movies.length
      Noop //SetHtml("", ) ?? the right choice ??
    }

    val initialMovies = MovieBase.getMovies(0, currentSize + MovieService.loadSize)
    "@movies" #> initialMovies.map(movie =>
      "@genre" #> movie.genre &
        "@name" #> movie.name &
        "@length" #> movie.length
    ) &
      "@partialLoad [onclick]" #> SHtml.ajaxInvoke(() => partialLoad())
  }

}