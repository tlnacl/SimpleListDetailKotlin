package nz.co.sush.simplelistdetailkotlin.network

import nz.co.sush.simplelistdetailkotlin.BuildConfig
import nz.co.sush.simplelistdetailkotlin.model.ForecastResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
    suspend fun getForcastByCity(@Query("id") cityId: Int): ForecastResult



    companion object {
        fun get(): ApiAdapter {
            val restAdapter = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()
            return restAdapter.create(ApiAdapter::class.java)
        }
    }
}