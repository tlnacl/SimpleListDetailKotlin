package nz.co.sush.simplelistdetailkotlin.network

import io.reactivex.Observable
import nz.co.sush.simplelistdetailkotlin.model.ForecastResult
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
}