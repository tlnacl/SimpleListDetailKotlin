package nz.co.sush.simplelistdetailkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import nz.co.sush.simplelistdetailkotlin.utils.bindView

class MainActivity : AppCompatActivity() {
    val recycleView:RecyclerView by bindView(R.id.recycler_view)

    private var adapter: EventListAdapter = EventListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        adapter.items = items
    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8"
    )
}
