package kernycnhyi.vlad.imdbapi.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kernycnhyi.vlad.imdbapi.api.ApiFactory
import kernycnhyi.vlad.imdbapi.db.ApiQueryDatabase
import kernycnhyi.vlad.imdbapi.interfaces.RecyclerMediaView
import kernycnhyi.vlad.imdbapi.model.BaseMovieModel
import moxy.InjectViewState

@InjectViewState
class ApiPresenter : BasePresenter<RecyclerMediaView>() {

    init {
        if (ApiFactory.CONNECTION) {
            withConnectionData()
        } else {
            fromDbData()
        }
    }

    fun withConnectionData() {
        ApiFactory.apiService.getAllMoviesByTitle("Joker")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { model ->
                model.search?.map {
                    ApiQueryDatabase.db?.queryDao()?.insertQuery(it)
                }
                model
            }
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.search?.map { model ->
                    isYoungerAndTypeCondition(model)
                }
            }
            .subscribe({
                it?.let {
                    viewState.showMovies(it)
                }
            }, {
                it.printStackTrace()
                viewState.showError(it.message)
            }).unsubscribeOnDestroy()
    }

    fun fromDbData() {
        ApiQueryDatabase.db!!.queryDao().getAllQueries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { model ->
                model.map {
                    isYoungerAndTypeCondition(it)
                }
            }
            .subscribe({
                it?.let { viewState.showMovies(it) }
            }, {
                viewState.showError(it.message)
            }).unsubscribeOnDestroy()
    }

    private fun isYoungerAndTypeCondition(model: BaseMovieModel): BaseMovieModel {
        if (model.year.take(4).toInt() >= YEAR)
            model.isYounger = true
        return if (model.type == "movie")
            model.baseToMovie()
        else
            model.baseToSeries()
    }

    companion object {
        const val YEAR = 2000
    }
}