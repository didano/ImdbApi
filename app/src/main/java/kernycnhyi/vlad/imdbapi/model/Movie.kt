package kernycnhyi.vlad.imdbapi.model

data class Movie(
    val movieTitle: String,
    val movieYear: String,
    val moviePoster: String,
    val movieType: String,
    var movieYounger: Boolean
) : BaseMovieModel(movieTitle, movieYear, moviePoster, movieType, movieYounger)

