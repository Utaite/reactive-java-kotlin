package chapter4.etc

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class DelayExample_ {

    fun marbleDiagram() {
        CommonUtils_.exampleStart()
        val data: Array<String> = arrayOf("1", "7", "2", "3", "4")
        val source = Observable.fromArray(*data)
                .delay(100L, TimeUnit.MILLISECONDS)
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = DelayExample_()
    demo.marbleDiagram()
}
