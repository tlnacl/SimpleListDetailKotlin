package nz.co.sush.simplelistdetailkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.antonioleiva.weatherapp.data.server.convertToDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import nz.co.sush.simplelistdetailkotlin.App
import nz.co.sush.simplelistdetailkotlin.R
import nz.co.sush.simplelistdetailkotlin.model.ForecastResult
import nz.co.sush.simplelistdetailkotlin.network.ApiAdapter
import nz.co.sush.simplelistdetailkotlin.ui.adapters.ForecastListAdapter
import nz.co.sush.simplelistdetailkotlin.ui.model.ForecastList
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var api: ApiAdapter

    private var adapter: ForecastListAdapter = ForecastListAdapter() {
        //on item click
//        val intent = Intent()
//        intent.putExtra(DetailActivity.CITY_NAME,cityName)
//        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).appComponent.inject(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        val cityId = 2193733
        api.getForcastByCity(cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { fr: ForecastResult -> convertToDomain(cityId, fr) }
                .subscribe{t: ForecastList? -> adapter.weekForecast = t!!}
    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8"
    )
}
