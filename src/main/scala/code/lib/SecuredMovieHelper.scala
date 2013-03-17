package code.lib

import net.liftweb.http.rest.RestHelper
import net.liftweb.util.Helpers
import net.liftweb.util.Helpers._
import net.liftweb.http.Req
import java.util.Calendar


object SecuredMovieHelper extends RestHelper {

  val ensureLogin: PartialFunction[Req, Unit] = {
    case _ if day(now) == Calendar.SUNDAY => // simple test
  }

  serve {

    case "movies" :: "secured" :: "from" :: from :: "to" :: to :: Nil JsonGet _ =>
      for {
        fromAsInt <- Helpers.asInt(from) ?~ "*from* could not be converted to an integer!" ~> 400
        toAsInt <- Helpers.asInt(to) ?~ "*to* could not be converted to an integer!" ~> 400
      } yield MovieService.getMoviesAsJSON(fromAsInt, toAsInt)

  }

}
