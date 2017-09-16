package chapter2

import io.reactivex.Observable
import io.reactivex.functions.Consumer


class ObservableCreateExample_ {

    fun basic() {
        val source = Observable.create<Int> {
            it.onNext(100)
            it.onNext(200)
            it.onNext(300)
            it.onComplete()
        }
        source.subscribe { println(it) }
    }

    fun notSubscribe() {
        val source = Observable.create<Int> {
            it.onNext(100)
            it.onNext(200)
            it.onNext(300)
            it.onComplete()
        }
    }

    fun subscribeLambda() {
        val source = Observable.create<Int> { emitter ->
            emitter.onNext(100)
            emitter.onNext(200)
            emitter.onNext(300)
            emitter.onComplete()
        }
        source.subscribe { data -> println("Result : $data") }
    }

    fun subscribeAnonymously() {
        val source = Observable.create<Int> {
            it.onNext(100)
            it.onNext(200)
            it.onNext(300)
            it.onComplete()
        }
        source.subscribe(Consumer<Int> { data ->
            println("Result : $data")
        })
    }

}


fun main(args: Array<String>) {
    val demo = ObservableCreateExample_()
    demo.basic()
    demo.notSubscribe()
    demo.subscribeLambda()
    demo.subscribeAnonymously()
}
