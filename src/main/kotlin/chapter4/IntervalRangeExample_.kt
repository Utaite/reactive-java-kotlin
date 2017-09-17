package chapter4

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class IntervalRangeExample_ {

    fun printNumbers() {
        val source = Observable.intervalRange(1, 5, 100L, 100L, TimeUnit.MILLISECONDS)
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

    fun makeWithInterval() {
        val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { it + 1 }
                .take(5)
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = IntervalRangeExample_();
    demo.printNumbers()
    demo.makeWithInterval()
}
