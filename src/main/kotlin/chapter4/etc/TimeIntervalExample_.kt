package chapter4.etc

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable


class TimeIntervalExample_ {

    fun marbleDiagram() {
        val data: Array<String> = arrayOf("1", "3", "7")

        CommonUtils_.exampleStart()
        val source = Observable.fromArray(*data)
                .delay {
                    CommonUtils_.doSomething()
                    Observable.just(it)
                }
                .timeInterval()

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = TimeIntervalExample_()
    demo.marbleDiagram()
}
