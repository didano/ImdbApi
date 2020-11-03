package kernycnhyi.vlad.imdbapi

import android.app.Application
import kernycnhyi.vlad.imdbapi.api.ApiFactory

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        ApiFactory.createRetrofit()
    }
}