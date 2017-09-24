package chapter5.schedulers

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors


class ExecutorSchedulerExample_ {

    fun basic() {
        val THREAD_NUM = 10

        val data = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*data)
        val executor = Executors.newFixedThreadPool(THREAD_NUM)

        source.subscribeOn(Schedulers.from(executor))
                .subscribe { Log_.it(it) }
        source.subscribeOn(Schedulers.from(executor))
                .subscribe { Log_.it(it) }
        CommonUtils_.sleep(500)
    }

}


fun main(args: Array<String>) {
    val demo = ExecutorSchedulerExample_()
    demo.basic()
}
