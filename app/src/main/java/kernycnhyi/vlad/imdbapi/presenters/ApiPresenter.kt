package kernycnhyi.vlad.imdbapi.presenters

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kernycnhyi.vlad.imdbapi.api.ApiFactory
import kernycnhyi.vlad.imdbapi.interfaces.RecyclerMediaView
import moxy.InjectViewState

@InjectViewState
class ApiPresenter : BasePresenter<RecyclerMediaView>() {

    init {
        ApiFactory.apiService.getAllMoviesByTitle("Batman")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.search }
            .subscribe({
                it?.let {
                    viewState.showMovies(it)
                }
            },{
                viewState.showError(it.message)
            })
    }

}