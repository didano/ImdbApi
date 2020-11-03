package kernycnhyi.vlad.imdbapi

import android.os.Bundle
import android.util.Log
import kernycnhyi.vlad.imdbapi.api.ApiService
import kernycnhyi.vlad.imdbapi.interfaces.NavigationInterface
import kernycnhyi.vlad.imdbapi.model.Movie
import kernycnhyi.vlad.imdbapi.model.SearchList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BasicActivity(), NavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFirstFragment()
    }

    override fun openFirstFragment() {
        router.openFirstFrag()
    }
}
