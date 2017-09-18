package chapter4.transporm

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ConcatMapExample_ {

    fun marbleDiagram() {
        CommonUtils_.exampleStart()
        val balls = arrayOf("1", "3", "5")
        val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { balls[it] }
                .take(balls.size.toLong())
                .concatMap { ball ->
                    Observable.interval(200L, TimeUnit.MILLISECONDS)
                            .map { "$ball<>" }
                            .take(2)
                }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(2000)
    }

    fun interleaving() {
        CommonUtils_.exampleStart()
        val balls = arrayOf("1", "3", "5")
        val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { it.toInt() }
                .map { balls[it] }
                .take(balls.size.toLong())
                .flatMap { ball ->
                    Observable.interval(200L, TimeUnit.MILLISECONDS)
                            .map { "$ball<>" }
                            .take(2)
                }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(2000)
    }

}


fun main(args: Array<String>) {
    val demo = ConcatMapExample_()
    demo.marbleDiagram()
    demo.interleaving()
}
