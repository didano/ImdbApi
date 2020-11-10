package kernycnhyi.vlad.imdbapi.presenters

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kernycnhyi.vlad.imdbapi.api.ApiFactory
import kernycnhyi.vlad.imdbapi.interfaces.RecyclerMediaView
import moxy.InjectViewState

@InjectViewState
class ApiPresenter : BasePresenter<RecyclerMediaView>() {

    init {
        ApiFactory.apiService.getAllMoviesByTitle("Dead")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.search?.map { model ->
                    if (model.year.take(4).toInt() >= YEAR)
                        model.isYounger = true
                    if (model.type == "movie")
                        model.baseToMovie()
                    else
                        model.baseToSeries()
                }
            }
            .subscribe({
                it?.let {
                    viewState.showMovies(it)
                }
            }, {
                it.printStackTrace()
                viewState.showError(it.message)
            })
    }

    companion object {
        const val YEAR = 2000
    }
}