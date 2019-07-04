package nz.co.sush.simplelistdetailkotlin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import nz.co.sush.simplelistdetailkotlin.R
import nz.co.sush.simplelistdetailkotlin.ui.model.Forecast
import nz.co.sush.simplelistdetailkotlin.ui.model.ForecastList
import nz.co.sush.simplelistdetailkotlin.utils.toDateString
import kotlin.properties.Delegates

/**
 * Created by tlnacl on 24/02/16.
 */
class ForecastListAdapter(val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    var weekForecast:ForecastList by Delegates.observable(ForecastList(0,"","",emptyList())){
        d,old,new -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size()

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.get().load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}º"
                itemView.minTemperature.text = "${low.toString()}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}