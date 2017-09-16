package chapter2

import io.reactivex.Observable
import java.util.concurrent.Callable

class ObservableFromCallable_ {

    fun basic() {
        val callable = {
            Thread.sleep(1000)
            "Hello Callable"
        }

        val source = Observable.fromCallable(callable)
        source.subscribe { println(it) }
    }

    fun withoutLambda() {
        val callable: Callable<String> = Callable {
            Thread.sleep(1000)
            return@Callable "Hello Callable"
        }

        val source = Observable.fromCallable(callable)
        source.subscribe { println(it) }
    }

}


fun main(args: Array<String>) {
    val demo = ObservableFromCallable_()
    demo.basic()
    demo.withoutLambda()
}
