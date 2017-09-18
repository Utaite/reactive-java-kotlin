package chapter4.conditional

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class AmbExample_ {

    fun marbleDiagram() {
        val data1: Array<String> = arrayOf("1", "3", "5")
        val data2: Array<String> = arrayOf("2-R", "4-R")

        val sources = listOf(
                Observable.fromArray(*data1)
                        .doOnComplete { Log_.it("Observable #1: onComplete()") },
                Observable.fromArray(*data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete { Log_.it("Observable #2: onComplete()") }
        )

        Observable.amb(sources)
                .doOnComplete { Log_.it("Result : onComplete()") }
                .subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = AmbExample_()
    demo.marbleDiagram()
}
