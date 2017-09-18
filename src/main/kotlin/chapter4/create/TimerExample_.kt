package chapter4.create

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class TimerExample_ {

    fun showTime() {
        CommonUtils_.exampleStart()
        val source = Observable.timer(500L, TimeUnit.MILLISECONDS)
                .map { SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date()) }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = TimerExample_()
    demo.showTime()
}