package chapter4.combine

import common.CommonUtils_
import common.Log_
import common.Shape_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import java.util.concurrent.TimeUnit


class ZipExample_ {

    fun marbleDiagram() {
        val shapes = arrayOf("BALL", "PENTAGON", "STAR")
        val coloredTriangles = arrayOf("2-T", "6-T", "4-T")

        val source = Observable.zip(
                Observable.fromArray(*shapes).map { Shape_.getSuffix(it) },
                Observable.fromArray(*coloredTriangles).map { Shape_.getColor(it) },
                BiFunction { suffix: String, color: String -> color + suffix })

        source.subscribe { Log_.it(it) }
    }

    fun zipNumbers() {
        val source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                Function3 { a: Int, b: Int, c: Int -> a + b + c })

        source.subscribe { Log_.it(it) }
    }

    fun zipInterval() {
        val source = Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                BiFunction { value: String, _: Long -> value })

        CommonUtils_.exampleStart()
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

    fun zipWithNumbers() {
        val source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                BiFunction { a: Int, b: Int -> a + b })
                .zipWith(Observable.just(1, 2, 3), BiFunction { ab: Int, c: Int -> ab + c })

        source.subscribe { Log_.it(it) }
    }

}


fun main(args: Array<String>) {
    val demo = ZipExample_()
    demo.marbleDiagram()
    demo.zipNumbers()
    demo.zipInterval()
    demo.zipWithNumbers()
}
