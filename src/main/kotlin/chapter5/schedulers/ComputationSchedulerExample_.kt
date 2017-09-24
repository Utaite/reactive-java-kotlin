package chapter5.schedulers

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class ComputationSchedulerExample_ {

    fun basic() {
        CommonUtils_.exampleStart()

        val objs = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*objs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        BiFunction { t1: String, _: Long -> t1 })

        source.map { "<<$it>>" }
                .subscribe { Log_.it(it) }

        source.map { "##$it##" }
                .subscribe { Log_.it(it) }

        CommonUtils_.sleep(500)
    }

}


fun main(args: Array<String>) {
    val demo = ComputationSchedulerExample_()
    demo.basic()
}
