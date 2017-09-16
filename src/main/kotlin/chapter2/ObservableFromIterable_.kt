package chapter2

import common.Order
import common.Order_
import io.reactivex.Observable
import java.util.concurrent.ArrayBlockingQueue


class ObservableFromIterable_ {

    fun listExample() {
        val names: MutableList<String> = mutableListOf()
        names.add("Jerry")
        names.add("Jerry")
        names.add("William")
        names.add("Bob")

        val source = Observable.fromIterable(names)
        source.subscribe { println(it) }
    }

    fun setExample() {
        val cities: MutableSet<String> = mutableSetOf()
        cities.add("Seoul")
        cities.add("London")
        cities.add("Paris")

        val source = Observable.fromIterable(cities)
        source.subscribe { println(it) }
    }

    fun blockingQueueExample() {
        val orderQueue = ArrayBlockingQueue<Order_>(100)
        orderQueue.add(Order_("ORD-1"))
        orderQueue.add(Order_("ORD-2"))
        orderQueue.add(Order_("ORD-3"))

        val source = Observable.fromIterable<Order_>(orderQueue)
        source.subscribe { println(it.id) }
    }

}


fun main(args: Array<String>) {
    val demo = ObservableFromIterable_()
    demo.listExample()
    demo.setExample()
    demo.blockingQueueExample()
}
