package chapter2

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class ConnectableObservableExample_ {

    fun marbleDiagram() {
        val dt = arrayOf("1", "3", "5")
        val balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { dt[it] }
                .take(dt.size.toLong())
        val source = balls.publish()
        source.subscribe { println("Subscriber #1 -> $it") }
        source.subscribe { println("Subscriber #2 -> $it") }
        source.connect()

        Thread.sleep(250)
        source.subscribe { println("Subscriber #3 -> $it") }
        Thread.sleep(50)
    }

}


fun main(args: Array<String>) {
    val demo = ConnectableObservableExample_()
    demo.marbleDiagram()
}
