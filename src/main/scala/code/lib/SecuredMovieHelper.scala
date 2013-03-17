package code.lib

import net.liftweb.http.rest.RestHelper
import net.liftweb.util.Helpers
import net.liftweb.http.Req
import org.joda.time.{DateTimeConstants, DateTime}


object SecuredMovieHelper extends RestHelper {

  /*
   you are only allowed to use that secured service on sunday
   */
  val ensureLogin: PartialFunction[Req, Unit] = {
    case _ if new DateTime().getDayOfWeek() == DateTimeConstants.SUNDAY  =>
  }

  serve {

    case "movies" :: "secured" :: "from" :: from :: "to" :: to :: Nil JsonGet _ =>
      for {
        fromAsInt <- Helpers.asInt(from) ?~ "*from* could not be converted to an integer!" ~> 400
        toAsInt <- Helpers.asInt(to) ?~ "*to* could not be converted to an integer!" ~> 400
      } yield MovieService.getMoviesAsJSON(fromAsInt, toAsInt)

  }

}
