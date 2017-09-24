package chapter5.schedulers

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class SingleSchedulerExample_ {

    fun basic() {
        val numbers = Observable.range(100, 5)
        val chars = Observable.range(0, 5)
                .map { CommonUtils_.numberToAlphabet(it) }

        numbers.subscribeOn(Schedulers.single())
                .subscribe { Log_.it(it) }
        chars.subscribeOn(Schedulers.single())
                .subscribe { Log_.it(it) }

        CommonUtils_.sleep(500)
    }

}

fun main(args: Array<String>) {
    val demo = SingleSchedulerExample_()
    demo.basic()
}
