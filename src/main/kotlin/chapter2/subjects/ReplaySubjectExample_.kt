package chapter2.subjects

import io.reactivex.subjects.ReplaySubject

class ReplaySubjectExample_ {

    fun marbleDiagram() {
        val subject = ReplaySubject.create<String>()
        subject.subscribe { println("Subscriber #1 -> $it") }
        subject.onNext("1")
        subject.onNext("3")
        subject.subscribe { println("Subscriber #2 -> $it") }
        subject.onNext("5")
        subject.onComplete()
    }

}


fun main(args: Array<String>) {
    val demo = ReplaySubjectExample_()
    demo.marbleDiagram()
}
