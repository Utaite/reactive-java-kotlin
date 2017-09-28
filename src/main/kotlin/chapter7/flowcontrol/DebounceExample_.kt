package chapter7.flowcontrol

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class DebounceExample_ {

    fun marbleDiagram() {
        val data = arrayOf("1", "2", "3", "5")
        val source = Observable.concat(
                Observable.timer(100L, TimeUnit.MILLISECONDS).map { data[0] },
                Observable.timer(300L, TimeUnit.MILLISECONDS).map { data[1] },
                Observable.timer(100L, TimeUnit.MILLISECONDS).map { data[2] },
                Observable.timer(300L, TimeUnit.MILLISECONDS).map { data[3] }
        ).debounce(200L, TimeUnit.MILLISECONDS)

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = DebounceExample_()
    demo.marbleDiagram()
}
