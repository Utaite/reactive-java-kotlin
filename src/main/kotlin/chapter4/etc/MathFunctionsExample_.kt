package chapter4.etc

import common.Log_
import hu.akarnokd.rxjava2.math.MathFlowable
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable


class MathFunctionsExample_ {

    fun marbleDiagram() {
        val data: Array<Int> = arrayOf(1, 2, 3, 4)

        // 1. Count
        val source = Observable.fromArray(*data)
                .count()
        source.subscribe { count -> Log_.it("count is $count") }

        // 2. max() & min()
        Flowable.fromArray(*data)
                .to { MathFlowable.max(it) }
        source.subscribe { max -> Log_.it("max is $max") }

        Flowable.fromArray(*data)
                .to { MathFlowable.min(it) }
                .subscribe { min -> Log_.it("min is $min") }

        // 3. sum() & average
        val flowable = Flowable.fromArray(*data)
                .to { MathFlowable.sumInt(it) }
        flowable.subscribe { sum -> Log_.it("sum is $sum") }

        val flowable2 = Observable.fromArray(*data)
                .toFlowable(BackpressureStrategy.BUFFER)
                .to { MathFlowable.averageDouble(it) }
        flowable2.subscribe { avg -> Log_.it("average is $avg") }
    }

}


fun main(args: Array<String>) {
    val demo = MathFunctionsExample_()
    demo.marbleDiagram()
}
