package chapter4.create

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class IntervalExample_ {

    fun printNumbers() {
        CommonUtils_.exampleStart()
        val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { (it + 1) * 100 }
                .take(5)
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

    fun noInitialDelay() {
        CommonUtils_.exampleStart()
        val source = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map { it + 100 }
                .take(5)
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = IntervalExample_()
    demo.printNumbers()
    demo.noInitialDelay()
}
