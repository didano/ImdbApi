package kernycnhyi.vlad.imdbapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("Title")
    @Expose
    val title: String,

    @SerializedName("Year")
    @Expose
    val year: String,

    @SerializedName("Poster")
    @Expose
    var poster: String,

    @SerializedName("Type")
    @Expose
    var type: String,
    var isYounger: Boolean = false
)
