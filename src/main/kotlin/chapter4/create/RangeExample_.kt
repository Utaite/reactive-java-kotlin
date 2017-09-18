package chapter4.create

import common.Log_
import io.reactivex.Observable


class RangeExample_ {

    fun forLoop() {
        val source = Observable.range(1, 10)
                .filter { it % 2 == 0 }
        source.subscribe { Log_.it(it) }
    }

}


fun main(args: Array<String>) {
    val demo = RangeExample_()
    demo.forLoop()
}
