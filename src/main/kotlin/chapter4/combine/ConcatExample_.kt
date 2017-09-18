package chapter4.combine

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class ConcatExample_ {

    fun marbleDiagram() {
        val onCompleteAction = { Log_.it("onComplete()") }

        val data1: Array<String> = arrayOf("1", "3", "5")
        val data2: Array<String> = arrayOf("2", "4", "6")
        val source1 = Observable.fromArray(*data1)
                .doOnComplete { onCompleteAction() }
        val source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { data2[it] }
                .take(data2.size.toLong())
                .doOnComplete { onCompleteAction() }

        val source = Observable.concat(source1, source2)
                .doOnComplete { onCompleteAction() }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = ConcatExample_()
    demo.marbleDiagram()
}
