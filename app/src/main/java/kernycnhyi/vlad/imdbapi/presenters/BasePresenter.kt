package kernycnhyi.vlad.imdbapi.presenters

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView


open class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    open var dispoable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        dispoable.dispose()
    }

    fun Disposable.unsubscribeOnDestroy(): Disposable {
        dispoable.add(this)
        return this
    }

}