package nz.co.sush.simplelistdetailkotlin.ui.activities

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import nz.co.sush.simplelistdetailkotlin.R
import nz.co.sush.simplelistdetailkotlin.ui.model.Forecast

/**
 * Created by tom.t on 3/7/2016.
 */
class DetailActivity: AppCompatActivity() {
    companion object {
        val FORECAST = "DetailActivity:forecast"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail)

//        toolbarTitle = intent.getStringExtra(CITY_NAME)
//        enableHomeAsUp { onBackPressed() }

//        async() {
//            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
//            uiThread { bindForecast(result) }
//        }


    }

    private fun bindForecast(forecast: Forecast,context: Context) = with(forecast) {
        Picasso.get().load(iconUrl).into(icon)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.setTextColor(ContextCompat.getColor(this,(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })))
    }
}