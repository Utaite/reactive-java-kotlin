package chapter4.combine

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class MergeExample_ {

    fun marbleDiagram() {
        val data1: Array<String> = arrayOf("1", "3")
        val data2: Array<String> = arrayOf("2", "4", "6")

        val source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { data1[it] }
                .take(data1.size.toLong())
        val source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { data2[it] }
                .take(data2.size.toLong())

        val source = Observable.merge(source1, source2);

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = MergeExample_()
    demo.marbleDiagram()
}
