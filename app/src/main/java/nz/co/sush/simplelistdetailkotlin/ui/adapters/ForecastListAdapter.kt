package nz.co.sush.simplelistdetailkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import nz.co.sush.simplelistdetailkotlin.R
import nz.co.sush.simplelistdetailkotlin.ui.model.Forecast
import nz.co.sush.simplelistdetailkotlin.ui.model.ForecastList
import nz.co.sush.simplelistdetailkotlin.utils.toDateString

/**
 * Created by tlnacl on 24/02/16.
 */
class ForecastListAdapter : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    private lateinit var weekForecast: ForecastList
    private lateinit var callback: Callback
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view)
    }

    fun setForecast(weekForecast: ForecastList) {
        this.weekForecast = weekForecast
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size()

    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}º"
                itemView.minTemperature.text = "${low.toString()}º"
                itemView.setOnClickListener { callback.onItemClick(forecast) }
            }
        }
    }

    interface Callback {
        fun onItemClick(forecast: Forecast)
    }
}