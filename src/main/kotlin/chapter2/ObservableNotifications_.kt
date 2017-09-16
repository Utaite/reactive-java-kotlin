package chapter2

import io.reactivex.Observable
import io.reactivex.disposables.Disposable


class ObservableNotifications_ {

    fun basic() {
        val source = Observable.just("RED", "GREEN", "YELLOW")

        /*
        val d: Disposable = source.subscribe(
                { v -> println("onNext() : value : $v") },
                { err -> println("onError() : err : ${err.message}") },
                { println("onComplete()") }
        )
        */

        val d: Disposable = source.subscribe({
            println("onNext() : value : $it")
        }, {
            println("onError() : err : ${it.message}")
        }, {
            println("onComplete()")
        })

        println("isDisposed() : ${d.isDisposed}")
    }

}


fun main(args: Array<String>) {
    val demo = ObservableNotifications_()
    demo.basic()
}
