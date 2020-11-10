package kernycnhyi.vlad.imdbapi.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class SearchList(
    @SerializedName("Search")
    @Expose
    val search: List<BaseMovieModel>? = null
)