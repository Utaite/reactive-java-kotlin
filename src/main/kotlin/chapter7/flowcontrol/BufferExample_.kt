package chapter7.flowcontrol

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class BufferExample_ {

    fun marbleDiagram() {
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        CommonUtils_.exampleStart()

        // 앞의 3개는 100ms 간격으로 발행
        val earlySource = Observable.fromArray(*data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        val middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        val lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        val source = Observable.concat(earlySource, middleSource, lateSource)
                .buffer(3)

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

    fun bufferSkip() {
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        CommonUtils_.exampleStart()

        // 앞의 3개는 100ms 간격으로 발행
        val earlySource = Observable.fromArray(*data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        val middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        val lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        val source = Observable.concat(earlySource, middleSource, lateSource)
                .buffer(2, 3)

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = BufferExample_()
    // demo.marbleDiagram()
    demo.bufferSkip()
}
