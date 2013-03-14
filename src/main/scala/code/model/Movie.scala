package code.model

case class Movie(name: String, genre: String, length: Int) {
  val id: String = MovieBase.generateId()
}

object MovieBase {

  private val agent = "Agententhriller"
  private val sciFi = "Science Fiction"
  private val marvel = "Comic Verfilmung"
  private val fantasy = "Fantasy"

  private var id = 0

  var movies: List[Movie] =
    Movie("James Bond jagt Dr. No", agent, 104) ::
      Movie("I Robot", sciFi, 131) ::
      Movie("Der Herr der Ringe - Die Gefaehrten", fantasy, 178) ::
      Movie("The Avengers", marvel, 145) ::
      Movie("Der Herr der Ringe - Die zwei Tuerme", fantasy, 184) ::
      Movie("Goldfinger", agent, 113) ::
      Movie("The Hulk", marvel, 114) ::
      Movie("Skyfall", agent, 138) ::
      Movie("Der Herr der Ringe - Die Rueckkehr des Koenigs", fantasy, 198) ::
      Movie("Batman begins", marvel, 139) ::
      Movie("Star Trek", sciFi, 121) ::
      Movie("The dark knight", marvel, 165) ::
      Movie("Ironman", marvel, 121) ::
      Movie("The dark knight rises", marvel, 168) ::
      Nil

  def generateId(): String = {
    val genId = id.toString
    id = id + 1
    genId
  }

  def addMovie(movie: Movie) = movies ::= movie

  def findMovie(id: String): Option[Movie] = movies.find(_.id.equals(id))

  def getMovies(from: Int, until: Int): List[Movie] = movies.slice(from, until)
}