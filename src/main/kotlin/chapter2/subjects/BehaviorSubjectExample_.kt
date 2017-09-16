package chapter2.subjects

import io.reactivex.subjects.BehaviorSubject

class BehaviorSubjectExample_ {

    fun marbleDiagram() {
        val subject = BehaviorSubject.createDefault("6")
        subject.subscribe { println("Subscriber #1 -> $it") }
        subject.onNext("1")
        subject.onNext("3")
        subject.subscribe { println("Subscriber #2 -> $it") }
        subject.onNext("5")
        subject.onComplete()
    }

}


fun main(args: Array<String>) {
    val demo = BehaviorSubjectExample_()
    demo.marbleDiagram()
}
