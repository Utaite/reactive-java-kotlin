package chapter4.combine

import common.CommonUtils_
import common.Log_
import common.Shape_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class CombineLatestExample_ {

    fun marbleDiagram() {
        val data1 = arrayOf("6", "7", "4", "2")
        val data2 = arrayOf("DIAMOND", "STAR", "PENTAGON")

        val source = Observable.combineLatest(
                Observable.fromArray(*data1)
                        .zipWith<Long, String>(Observable.interval(100L, TimeUnit.MILLISECONDS),
                                BiFunction { shape, _ -> Shape_.getColor(shape) }),
                Observable.fromArray(*data2)
                        .zipWith<Long, String>(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                                BiFunction { shape, _ -> Shape_.getSuffix(shape) }),
                BiFunction { v1: String, v2: String -> v1 + v2 })

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = CombineLatestExample_()
    demo.marbleDiagram()
}
