package chapter3

import io.reactivex.Observable
import io.reactivex.Single


class FilterExample_ {

    fun marbleDiagram() {
        val objs = arrayOf("1 CIRCLE", "2 DIAMOND", "3TRIANGLE ", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON")
        val source = Observable.fromArray(*objs)
                .filter { it.endsWith("CIRCLE") }
        source.subscribe { println(it) }
    }

    fun usingPredicate() {
        val filterCircle = { obj: String -> obj.endsWith("CIRCLE") }

        val objs = arrayOf("RED CIRCLE", "YELLOW DIAMOND", "GREEN TRIANGLE", "SKY DIAMOND", "BLUE CIRCLE", "PURPLE HEXAGON")
        val source = Observable.fromArray(*objs)
                .filter { filterCircle(it) }
        source.subscribe { println(it) }
    }

    fun evenNumbers() {
        val data = arrayOf(100, 34, 27, 99, 50)
        val source = Observable.fromArray(*data)
                .filter { it % 2 == 0 }
        source.subscribe { println(it) }
    }

    fun otherFilters() {
        val numbers = arrayOf(100, 200, 300, 400, 500)
        var single: Single<Int>
        var source: Observable<Int>

        // 1. first
        single = Observable.fromArray(*numbers).first(-1)
        single.subscribe { data -> println("first() value = $data") }

        // 2. last
        single = Observable.fromArray(*numbers).last(999)
        single.subscribe { data -> println("last() value = $data") }

        // 3. take(N)
        source = Observable.fromArray(*numbers).take(3)
        source.subscribe { println("take(3) values = $it") }

        // 4. takeLast(N)
        source = Observable.fromArray(*numbers).takeLast(3)
        source.subscribe { println("takeLast(3) values = $it") }

        // 5. skip(N)
        source = Observable.fromArray(*numbers).skip(2)
        source.subscribe { println("skip(2) values = $it") }

        // 6. skipLast(N)
        source = Observable.fromArray(*numbers).skipLast(2)
        source.subscribe { println("skipLast(2) values = $it") }
    }

}


fun main(args: Array<String>) {
    val demo = FilterExample_()
    demo.marbleDiagram()
    demo.usingPredicate()
    demo.evenNumbers()
    demo.otherFilters()
}
