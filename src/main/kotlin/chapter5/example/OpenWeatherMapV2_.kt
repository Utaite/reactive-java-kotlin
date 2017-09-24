package chapter5.example

import common.CommonUtils_
import common.Log_
import common.OkHttpHelper_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern


class OpenWeatherMapV2_ {

    private val URL = "http://api.openweathermap.org/data/2.5/weather?q=london&APPID="

    private val API_KEY = "0a2bb8c0d43e212ec8ebd04613b183a3"

    fun run() {
        CommonUtils_.exampleStart()

        val source = Observable.just(URL + API_KEY)
                .map { OkHttpHelper_.getWithLog(it) }
                .subscribeOn(Schedulers.io())
                .share()
                .observeOn(Schedulers.newThread())

        source.map { parseTemperature(it) }.subscribe { Log_.it(it) }
        source.map { parseCityName(it) }.subscribe { Log_.it(it) }
        source.map { parseCountry(it) }.subscribe { Log_.it(it) }

        CommonUtils_.sleep(10000)
    }

    private fun parseTemperature(json: String): String =
            parse(json, "\"temp\":[0-9]*.[0-9]*")

    private fun parseCityName(json: String): String =
            parse(json, "\"name\":\"[a-zA-Z]*\"")

    private fun parseCountry(json: String): String =
            parse(json, "\"country\":\"[a-zA-Z]*\"")

    private fun parse(json: String, regex: String): String {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(json)
        return when (matcher.find()) {
            false -> "N/A"
            true -> matcher.group()
        }
    }

}

fun main(args: Array<String>) {
    val demo = OpenWeatherMapV2_()
    demo.run()
}