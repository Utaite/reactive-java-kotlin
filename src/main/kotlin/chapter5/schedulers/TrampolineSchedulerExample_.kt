package chapter5.schedulers

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class TrampolineSchedulerExample_ {

    fun basic() {
        val objs = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*objs)

        source.subscribeOn(Schedulers.trampoline())
                .map { "<<$it>>" }
                .subscribe { Log_.it(it) }

        source.subscribeOn(Schedulers.trampoline())
                .map { "##$it##" }
                .subscribe { Log_.it(it) }
        CommonUtils_.sleep(500)
    }

}


fun main(args: Array<String>) {
    val demo = TrampolineSchedulerExample_()
    demo.basic()
}
