package kernycnhyi.vlad.imdbapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseMovieModel(
    @SerializedName("Title")
    @Expose
    val title: String,

    @SerializedName("Year")
    @Expose
    val year: String,

    @SerializedName("Poster")
    @Expose
    val poster: String,

    @SerializedName("Type")
    @Expose
    val type: String,
    var isYounger: Boolean
) {
    fun baseToMovie(): Movie = Movie(title, year, poster, type, isYounger)
    fun baseToSeries(): Series = Series(title, year, poster, type, isYounger)
}