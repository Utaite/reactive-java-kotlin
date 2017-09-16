package chapter3

import io.reactivex.Observable


class ReduceExample_ {

    fun marbleDiagram() {
        val balls = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*balls)
                .reduce { ball1, ball2 -> "$ball2($ball1)" }
        source.subscribe { println(it) }
    }

    fun biFunction() {
        val mergeBalls = { ball1: String, ball2: String -> "$ball2($ball1)" }
        val balls = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*balls)
                .reduce { t1, t2 -> mergeBalls(t1, t2) }
        source.subscribe { println(it) }
    }

}


fun main(args: Array<String>) {
    val demo = ReduceExample_()
    demo.marbleDiagram()
    demo.biFunction()
}