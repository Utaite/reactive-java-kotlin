package chapter3

import io.reactivex.Observable


class FlatMapExample_ {

    fun marbleDiagram() {
        val getDoubleDiamonds = { ball: String -> Observable.just<String>(ball + "<>", ball + "<>") }

        val balls = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*balls)
                .flatMap { getDoubleDiamonds(it) }
        source.subscribe { println(it) }
    }

    fun flatMapLambda() {
        val balls = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*balls)
                .flatMap { Observable.just(it + "<>", it + "<>") }
        source.subscribe { println(it) }
    }

}


fun main(args: Array<String>) {
    val demo = FlatMapExample_()
    demo.marbleDiagram()
    demo.flatMapLambda()
}
