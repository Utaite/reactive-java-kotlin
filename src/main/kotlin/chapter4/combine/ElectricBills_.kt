package chapter4.combine

import common.Log
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import java.text.DecimalFormat

class ElectricBills_ {

    private var index = 0

    fun electricBillV1() {
        val data = arrayOf("100", "300")

        val basePrice = Observable.fromArray(*data)
                .map { it.toInt() }
                .map {
                    when {
                        it <= 200 -> 910
                        it <= 400 -> 1600
                        else -> 7300
                    }
                }

        val usagePrice = Observable.fromArray(*data)
                .map { it.toInt() }
                .map {
                    val series1 = minOf(200, it) * 93.3
                    val series2 = minOf(200, maxOf(it - 200, 0)) * 187.9
                    val series3 = minOf(0, maxOf(it - 400, 0)) * 280.65
                    series1 + series2 + series3
                }
                .map { it.toInt() }

        val source = Observable.zip(basePrice, usagePrice,
                BiFunction { t1: Int, t2: Int -> t1 + t2 })

        source.map { DecimalFormat("#,###").format(it) }
                .subscribe {
                    val sb = StringBuilder()
                    sb.append("Usage: ${data[index]} kWh -> ")
                    sb.append("Price: ${it}원")
                    Log.it(sb.toString())

                    index++
                }
    }

    fun electricBillV2() {
        val data = arrayOf("100", "300")

        val basePrice = Observable.fromArray(*data)
                .map { it.toInt() }
                .map {
                    when {
                        it <= 200 -> 910
                        it <= 400 -> 1600
                        else -> 7300
                    }
                }

        val usagePrice = Observable.fromArray(*data)
                .map { it.toInt() }
                .map {
                    val series1 = minOf(200, it) * 93.3
                    val series2 = minOf(200, maxOf(it - 200, 0)) * 187.9
                    val series3 = minOf(0, maxOf(it - 400, 0)) * 280.65
                    series1 + series2 + series3
                }
                .map { it.toInt() }

        val source = Observable.zip(basePrice, usagePrice, Observable.fromArray(*data),
                Function3 { t1: Int, t2: Int, t3: String -> t3 to t1 + t2 })

        source.map { it.first to DecimalFormat("#,###").format(it.second) }
                .subscribe {
                    val sb = StringBuilder()
                    sb.append("Usage: ${it.first} kWh -> ")
                    sb.append("Price: ${it.second}원")
                    Log.it(sb.toString())
                }
    }

}


fun main(args: Array<String>) {
    val demo = ElectricBills_()
    demo.electricBillV1()
    demo.electricBillV2()
}
