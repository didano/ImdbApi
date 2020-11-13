package kernycnhyi.vlad.imdbapi

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kernycnhyi.vlad.imdbapi.api.ApiFactory

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun View.isVisible(condition: Boolean) {
    visibility = if (condition)
        View.VISIBLE
    else
        View.GONE
}

fun Context.registerNetworkCallbacks() {
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            ApiFactory.CONNECTION = true
        }

        override fun onLost(network: Network) {
            ApiFactory.CONNECTION = false
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