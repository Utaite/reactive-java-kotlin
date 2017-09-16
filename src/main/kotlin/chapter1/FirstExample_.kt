package chapter1

import io.reactivex.Observable


class FirstExample_ {

    fun emit() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .subscribe { println(it) }
    }

}


fun main(args: Array<String>) {
    val demo = FirstExample_()
    demo.emit()
}
