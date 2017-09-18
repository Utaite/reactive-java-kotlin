package chapter4.transporm

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class SwitchMapExample_ {

    fun marbleDiagram() {
        CommonUtils_.exampleStart()
        val balls = arrayOf("1", "3", "5")
        val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { balls[it] }
                .take(balls.size.toLong())
                .switchMap { ball ->
                    Observable.interval(200L, TimeUnit.MILLISECONDS)
                            .map { "$ball<>" }
                            .take(2)
                }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(2000)
    }

    fun usingDoOnNext() {
        CommonUtils_.exampleStart()
        val balls = arrayOf("1", "3", "5")
        val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { balls[it] }
                .take(balls.size.toLong())
                .doOnNext { Log_.it(it) }
                .switchMap { ball ->
                    Observable.interval(200L, TimeUnit.MILLISECONDS)
                            .map { "$ball<>" }
                            .take(2)
                }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(2000)
    }

}


fun main(args: Array<String>) {
    val demo = SwitchMapExample_()
    demo.marbleDiagram()
    demo.usingDoOnNext()
}
