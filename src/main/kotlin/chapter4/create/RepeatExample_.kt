package chapter4.create

import common.CommonUtils_
import common.Log_
import common.OkHttpHelper_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class RepeatExample_ {

    fun marbleDiagram() {
        val balls = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*balls)
                .repeat(3)

        source.doOnComplete { Log_.it("onComplete") }
                .subscribe { Log_.it(it) }
    }

    fun heartbeatV1() {
        val serverUrl = "https://api.github.com/zen"

        Observable.timer(2, TimeUnit.SECONDS)
                .map { serverUrl }
                .map { OkHttpHelper_.get(it) }
                .repeat()
                .subscribe { Log_.it("Ping Result : $it") }
        CommonUtils_.sleep(10000)
    }

}


fun main(args: Array<String>) {
    val demo = RepeatExample_()
    demo.marbleDiagram()
    demo.heartbeatV1()
}
