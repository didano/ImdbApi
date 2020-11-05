package kernycnhyi.vlad.imdbapi.interfaces

import kernycnhyi.vlad.imdbapi.model.Movie
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType


interface RecyclerMediaView : MvpView {

    @StateStrategyType(value = AddToEndStrategy::class)
    fun showMovies(list: List<Movie>)

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun showError(error: String? = null)

}