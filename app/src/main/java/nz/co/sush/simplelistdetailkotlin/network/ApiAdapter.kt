package nz.co.sush.simplelistdetailkotlin.network

import io.reactivex.Observable
import nz.co.sush.simplelistdetailkotlin.BuildConfig
import nz.co.sush.simplelistdetailkotlin.model.ForecastResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by tomtang on 18/02/16.
 */
interface ApiAdapter {
    //TODO add appid in interceptor
    //cityid:2193733
    @GET("forecast/daily?mode=json&units=metric&cnt=7&APPID=4a296830ce66f74149cb8840cd37100f")
    fun getForcastByCity(@Query("id") cityId: Int): Observable<ForecastResult>



    companion object {
        fun get(): ApiAdapter {
            val httpClient = OkHttpClient.Builder().addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                        .build()
                chain.proceed(request);
            }.build()

            val restAdapter = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build()
            return restAdapter.create(ApiAdapter::class.java)
        }
    }
}