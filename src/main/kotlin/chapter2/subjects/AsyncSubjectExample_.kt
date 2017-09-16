package chapter2.subjects

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject


class AsyncSubjectExample_ {

    fun marbleDiagram() {
        val subject = AsyncSubject.create<String>()
        subject.subscribe({ data -> println("Subscriber #1 -> $data") })
        subject.onNext("1")
        subject.onNext("3")
        subject.subscribe({ data -> println("Subscriber #2 -> $data") })
        subject.onNext("5")
        subject.onComplete()
    }

    fun asSubscriber() {
        val temperature = arrayOf(10.1f, 13.4f, 12.5f)
        val source = Observable.fromArray(*temperature)

        val subject = AsyncSubject.create<Float>()
        subject.subscribe { println("Subscriber #1 -> $it") }

        source.subscribe(subject)
    }

    fun afterComplete() {
        val subject = AsyncSubject.create<Int>()
        subject.onNext(10)
        subject.onNext(11)
        subject.subscribe { println("Subscriber #1 -> $it") }
        subject.onNext(12)
        subject.onComplete()
        subject.onNext(13)
        subject.subscribe { println("Subscriber #1 -> $it") }
        subject.subscribe { println("Subscriber #1 -> $it") }
    }

}


fun main(args: Array<String>) {
    val demo = AsyncSubjectExample_()
    demo.marbleDiagram()
    demo.asSubscriber()
    demo.afterComplete()
}
