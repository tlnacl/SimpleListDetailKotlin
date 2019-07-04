/*
 * Copyright 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.co.sush.simplelistdetailkotlin.network

import nz.co.sush.simplelistdetailkotlin.model.Forecast
import nz.co.sush.simplelistdetailkotlin.model.ForecastResult
import nz.co.sush.simplelistdetailkotlin.ui.model.ForecastList
import nz.co.sush.simplelistdetailkotlin.ui.model.Forecast as ModelForecast


    fun convertToDomain(cityId: Int, forecast: ForecastResult) = with(forecast) {
        ForecastList(cityId, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt * 1000, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"