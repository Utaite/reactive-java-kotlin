package chapter5.schedulers

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class NewThreadSchedulerExample_ {

    fun basic() {
        val objs = arrayOf("1", "3", "5")
        Observable.fromArray(*objs)
                .doOnNext { Log_.it("Original data : $it") }
                .map { "<<$it>>" }
                .subscribeOn(Schedulers.newThread())
                .subscribe{ Log_.it(it) }
        CommonUtils_.sleep(500)

        Observable.fromArray(*objs)
                .doOnNext { Log_.it("Original data : $it") }
                .map { "##$it##" }
                .subscribeOn(Schedulers.newThread())
                .subscribe{ Log_.it(it) }
        CommonUtils_.sleep(500)
    }

}


fun main(args: Array<String>) {
    val demo = NewThreadSchedulerExample_()
    demo.basic()
}
