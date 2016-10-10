package nz.co.sush.simplelistdetailkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.antonioleiva.weatherapp.data.server.convertToDomain
import kotlinx.android.synthetic.main.activity_main.*
import nz.co.sush.simplelistdetailkotlin.R
import nz.co.sush.simplelistdetailkotlin.model.ForecastResult
import nz.co.sush.simplelistdetailkotlin.network.ApiAdapter
import nz.co.sush.simplelistdetailkotlin.ui.adapters.ForecastListAdapter
import nz.co.sush.simplelistdetailkotlin.ui.model.ForecastList
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private var adapter: ForecastListAdapter = ForecastListAdapter() {
        //on item click
//        val intent = Intent()
//        intent.putExtra(DetailActivity.CITY_NAME,cityName)
//        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        val cityId = 2193733
        ApiAdapter.get().getForcastByCity(cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { fr: ForecastResult -> convertToDomain(cityId, fr) }

        .subscribe(EventSubscriber())
    }

    private inner class EventSubscriber: Subscriber<ForecastList>() {
        override fun onError(p0: Throwable?) {

        }

        override fun onCompleted() {

        }

        override fun onNext(p0: ForecastList?) {
            adapter.weekForecast = p0!!
        }

    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8"
    )
}
