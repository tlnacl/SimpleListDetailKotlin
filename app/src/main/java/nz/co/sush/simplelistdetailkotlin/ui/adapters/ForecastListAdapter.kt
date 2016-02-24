package nz.co.sush.simplelistdetailkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import nz.co.sush.simplelistdetailkotlin.R
import nz.co.sush.simplelistdetailkotlin.ui.model.Forecast
import nz.co.sush.simplelistdetailkotlin.ui.model.ForecastList
import nz.co.sush.simplelistdetailkotlin.utils.bindView
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
        val dateView: TextView by bindView(R.id.date)
        val descriptionView: TextView by bindView(R.id.description)
        val maxTemperatureView: TextView by bindView(R.id.maxTemperature)
        val minTemperatureView: TextView by bindView(R.id.minTemperature)
        val iconView: ImageView by bindView(R.id.icon)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(iconView)
                dateView.text = date.toDateString()
                descriptionView.text = description
                maxTemperatureView.text = "${high.toString()}º"
                minTemperatureView.text = "${low.toString()}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}