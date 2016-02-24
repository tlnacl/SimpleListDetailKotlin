package nz.co.sush.simplelistdetailkotlin.ui.model

data class ForecastList(val id: Int, val city: String, val country: String, val dailyForecast: List<Forecast>) {
    operator fun get(position: Int) = dailyForecast[position]
    fun size() = dailyForecast.size
}

data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int,
                    val iconUrl: String)