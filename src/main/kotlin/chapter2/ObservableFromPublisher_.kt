package chapter2

import io.reactivex.Observable
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber


class ObservableFromPublisher_ {

    fun basic() {
        /*
        val publisher = { s: Subscriber<in String> ->
            s.onNext("Hello Observable.fromPublisher()")
            s.onComplete()
        }
        */
        val publisher = Publisher<String> {
            it.onNext("Hello Observable.fromPublisher()")
            it.onComplete()
        }

        val source = Observable.fromPublisher(publisher)
        source.subscribe { println(it) }
    }

    fun withoutLambda() {
        val publisher = object : Publisher<String> {
            override fun subscribe(s: Subscriber<in String>) {
                s.onNext("Hello Observable.fromPublisher()")
                s.onComplete()
            }
        }

        val source = Observable.fromPublisher<String>(publisher)
        source.subscribe { println(it) }
    }

}

fun main(args: Array<String>) {
    val demo = ObservableFromPublisher()
    demo.basic()
    demo.withoutLambda()
}
