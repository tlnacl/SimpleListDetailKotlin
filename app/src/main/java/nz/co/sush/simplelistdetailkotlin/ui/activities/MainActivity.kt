package nz.co.sush.simplelistdetailkotlin.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import nz.co.sush.simplelistdetailkotlin.R
import nz.co.sush.simplelistdetailkotlin.network.ApiAdapter
import nz.co.sush.simplelistdetailkotlin.network.convertToDomain
import nz.co.sush.simplelistdetailkotlin.ui.adapters.ForecastListAdapter
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job:Job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private var adapter: ForecastListAdapter = ForecastListAdapter {
        //on item click
        val intent = Intent()
        intent.putExtra(DetailActivity.CITY_NAME, "City Name")
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        val cityId = 2193733

//        GlobalScope.launch(Dispatchers.Main) {
//            adapter.weekForecast = convertToDomain(cityId, ApiAdapter.get().getForcastByCity(cityId))
//        }

        launch {
            adapter.weekForecast = convertToDomain(cityId, ApiAdapter.get().getForcastByCity(cityId))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8"
    )
}
