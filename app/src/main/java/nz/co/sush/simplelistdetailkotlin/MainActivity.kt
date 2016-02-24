package nz.co.sush.simplelistdetailkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.antonioleiva.weatherapp.data.server.convertToDomain
import nz.co.sush.simplelistdetailkotlin.model.ForecastResult
import nz.co.sush.simplelistdetailkotlin.network.ApiAdapter
import nz.co.sush.simplelistdetailkotlin.ui.adapters.ForecastListAdapter
import nz.co.sush.simplelistdetailkotlin.ui.model.ForecastList
import nz.co.sush.simplelistdetailkotlin.utils.bindView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
//    private val kodein = lazyKodeinFromApp()
//    private val apiAdapter: ApiAdapter by kodein.lazyProvider<ApiAdapter>()

    val recycleView: RecyclerView by bindView(R.id.recycler_view)

    private var adapter: ForecastListAdapter = ForecastListAdapter(){
        //on item click
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        //TODO by using DI
        val cityId = 2193733
        ApiAdapter.get().getForcastByCity(cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { fr: ForecastResult -> convertToDomain(cityId,fr) }

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
