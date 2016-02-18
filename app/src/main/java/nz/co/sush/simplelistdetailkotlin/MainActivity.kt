package nz.co.sush.simplelistdetailkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import nz.co.sush.simplelistdetailkotlin.model.Event
import nz.co.sush.simplelistdetailkotlin.network.ApiAdapter
import nz.co.sush.simplelistdetailkotlin.utils.bindView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val recycleView: RecyclerView by bindView(R.id.recycler_view)

    private var adapter: EventListAdapter = EventListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        adapter.items = items

        ApiAdapter.get().getEventList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
    }

    private inner class EventSubscriber: Subscriber<List<Event>>() {
        override fun onNext(p0: List<Event>?) {
//            adapter.items = p0.flatMap { e }
        }

        override fun onCompleted() {

        }

        override fun onError(p0: Throwable?) {

        }

    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8"
    )
}
