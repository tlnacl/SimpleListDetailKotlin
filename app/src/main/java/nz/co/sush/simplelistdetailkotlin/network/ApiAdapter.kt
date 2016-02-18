package nz.co.sush.simplelistdetailkotlin.network

import nz.co.sush.simplelistdetailkotlin.model.Event
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

/**
 * Created by tomtang on 18/02/16.
 */
interface ApiAdapter {
    @GET("/events")
    fun getEventList(): Observable<List<Event>>

    companion object {
        fun get(): ApiAdapter {
            val httpClient = OkHttpClient.Builder().addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                        .build()
                chain.proceed(request);
            }.build()

            val restAdapter = Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient)
                    .build()
            return restAdapter.create(ApiAdapter::class.java)
        }
    }
}