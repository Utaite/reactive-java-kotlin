package chapter4.conditional

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class SkipUntilExample_ {

    fun marbleDiagram() {
        val data: Array<String> = arrayOf("1", "2", "3", "4", "5", "6")

        val source = Observable.fromArray(*data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { t1: String, _: Long -> t1 })
                .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS))

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = SkipUntilExample_()
    demo.marbleDiagram()
}
