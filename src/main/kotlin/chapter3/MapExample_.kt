package chapter3

import io.reactivex.Observable


class MapExample_ {

    fun marbleDiagram() {
        val balls = arrayOf("1", "2", "3", "5")
        val source = Observable.fromArray(*balls)
                .map { it + "<>" }
        source.subscribe { println(it) }
    }

    fun mapFunction() {
        val getDiamond = { ball: String -> ball + "<>" }

        val balls = arrayOf("1", "2", "3", "5")
        /*
        val source = Observable.fromArray(*balls)
                .map(getDiamond)
        */
        val source = Observable.fromArray(*balls)
                .map { getDiamond(it) }
        source.subscribe { println(it) }
    }

    fun mappingType() {
        val ballToIndex = { ball: String ->
            when (ball) {
                "RED" -> 1
                "YELLOW" -> 2
                "GREEN" -> 3
                "BLUE" -> 5
                else -> -1
            }
        }

        val balls = arrayOf("RED", "YELLOW", "GREEN", "BLUE")
        val source = Observable.fromArray(*balls)
                .map { ballToIndex(it) }
        source.subscribe { println(it) }
    }

}


fun main(args: Array<String>) {
    val demo = MapExample_()
    demo.marbleDiagram()
    demo.mapFunction()
    demo.mappingType()
}