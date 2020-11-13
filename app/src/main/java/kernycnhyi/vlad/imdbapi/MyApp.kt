package kernycnhyi.vlad.imdbapi

import android.app.Application
import kernycnhyi.vlad.imdbapi.api.ApiFactory
import kernycnhyi.vlad.imdbapi.db.ApiQueryDatabase

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiFactory.createRetrofit()
        ApiQueryDatabase.createInstance(applicationContext)
        this.registerNetworkCallbacks()
    }
}