package chapter2

import common.Order_
import io.reactivex.Observable
import io.reactivex.Single


class SingleExample_ {

    fun just() {
        val source = Single.just("Hello Single")
        source.subscribe { data -> println(data) }
    }

    fun fromObservable() {
        // 1. 기존 Observable 에서 Single 객체로 변환하기
        val source = Observable.just("Hello Single")
        Single.fromObservable(source)
                .subscribe { data -> println(data) }

        // 2. single() 함수를 호출해 Single 객체 생성하기
        Observable.just("Hello Single")
                .single("default item")
                .subscribe { data -> println(data) }

        // 3. first() 함수를 호출해 Single 객체 생성하기
        val colors = arrayOf("Red", "Blue", "Gold")
        Observable.fromArray(*colors)
                .first("default value")
                .subscribe { data -> println(data) }

        // 4. empty Observable 에서 Single 객체 생성하기
        Observable.empty<Any>()
                .single("default value")
                .subscribe { data -> println(data) }

        // 5. take() 함수에서 Single 객체 생성하기
        Observable.just(Order_("ORD-1"), Order_("ORD-2"))
                .take(1)
                .single(Order_("default order"))
                .subscribe { data -> println(data.id) }
    }

    fun errorCase() {
        val source = Observable.just("Hello Single", "Error").single("default item")
        source.subscribe { data -> println(data) }
    }

}


fun main(args: Array<String>) {
    val demo = SingleExample_()
    demo.just()
    demo.fromObservable()
    // demo.errorCase()
}
