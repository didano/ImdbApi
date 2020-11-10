package kernycnhyi.vlad.imdbapi

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import kernycnhyi.vlad.imdbapi.api.ApiFactory
import kernycnhyi.vlad.imdbapi.db.ApiQueryDatabase

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiFactory.createRetrofit()
        ApiQueryDatabase.createInstance(applicationContext)
        registerNetworkCallbacks()
    }

    private fun registerNetworkCallbacks() {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                ApiFactory.CONNECTION = true
                Log.d("APIFACTORY", ApiFactory.CONNECTION.toString())
            }

            override fun onLost(network: Network) {
                ApiFactory.CONNECTION = false
                Log.d("APIFACTORY", ApiFactory.CONNECTION.toString())
            }
        }
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request =
                NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()
            cm.registerNetworkCallback(request, networkCallback)
        }
    }
}