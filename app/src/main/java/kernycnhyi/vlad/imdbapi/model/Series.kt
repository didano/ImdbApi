package kernycnhyi.vlad.imdbapi.model

data class Series (
    val seriesTitle: String,
    val seriesYear: String,
    val seriesPoster: String,
    val seriesType: String,
    var seriesYounger: Boolean
) : BaseMovieModel(seriesTitle, seriesYear, seriesPoster, seriesType, seriesYounger)