package kernycnhyi.vlad.imdbapi.api

import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "http://omdbapi.com/"
    private const val API_KEY_NAME = "apikey"
    private const val API_KEY = "514513d9"

    lateinit var apiService:ApiService

    fun createRetrofit() {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).addInterceptor {
                var request = it.request()
                val url = request.url
                    .newBuilder()
                    .addQueryParameter(API_KEY_NAME, API_KEY)
                    .build()
                request = request.newBuilder().url(url).build()
                it.proceed(request)
            }.build()

        apiService =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build().create(ApiService::class.java)
    }
}