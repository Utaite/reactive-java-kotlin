package chapter2

import io.reactivex.Observable
import java.util.concurrent.Executors

class ObservableFromFuture_ {

    fun basic() {
        val future = Executors.newSingleThreadExecutor().submit<String> {
            Thread.sleep(1000)
            "Hello Future"
        }

        val source = Observable.fromFuture(future)
        source.subscribe { println(it) }
    }

}


fun main(args: Array<String>) {
    val demo = ObservableFromFuture_()
    demo.basic()
}
