package chapter4.conditional

import common.Log_
import common.Shape_
import io.reactivex.Observable


class AllFunctionExample_ {

    fun marbleDiagram() {
        val data: Array<String> = arrayOf("1", "2", "3", "4")

        val source = Observable.fromArray(*data)
                .map { Shape_.getShape(it) }
                .all { it == Shape_.BALL }
        source.subscribe { it -> Log_.it(it) }
    }

}


fun main(args: Array<String>) {
    val demo = AllFunctionExample_()
    demo.marbleDiagram()
}
