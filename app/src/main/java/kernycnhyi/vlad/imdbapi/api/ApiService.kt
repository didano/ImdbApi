package kernycnhyi.vlad.imdbapi.api

import io.reactivex.rxjava3.core.Single
import kernycnhyi.vlad.imdbapi.model.Movie
import kernycnhyi.vlad.imdbapi.model.SearchList
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(".")
    fun getMovieByTitle(
        @Query(QUERY_SEARCH_ONE) title: String
    ): Single<Movie>

    @GET(".")
    fun getAllMoviesByTitle(
        @Query(QUERY_SEARCH_ALL) title: String
    ): Single<SearchList>

    companion object {
        const val QUERY_SEARCH_ONE = "t"
        const val QUERY_SEARCH_ALL = "s"
    }
}