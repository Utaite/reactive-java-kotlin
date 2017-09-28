package chapter7.flowcontrol

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class SampleExample_ {

    fun marbleDiagram() {
        val data = arrayOf("1", "7", "2", "3", "6")

        CommonUtils_.exampleStart()

        // 앞의 4개는 100ms 간격으로 발행
        val earlySource = Observable.fromArray(*data)
                .take(4)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 마지막 데이터는 300ms 후에 발행
        val lateSource = Observable.just(data[4])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 2개의 Observable을 결합하고 300ms로 샘플링
        val source = Observable.concat<String>(earlySource, lateSource)
                .sample(300L, TimeUnit.MILLISECONDS)

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

    fun emitLast() {
        val data = arrayOf("1", "7", "2", "3", "6")

        CommonUtils_.exampleStart()

        // 앞의 4개는 100ms 간격으로 발행
        val earlySource = Observable.fromArray(*data)
                .take(4)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 마지막 데이터는 300ms 후에 발행
        val lateSource = Observable.just(data[4])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 2개의 Observable을 결합하고 300ms로 샘플링
        val source = Observable.concat<String>(earlySource, lateSource)
                .sample(300L, TimeUnit.MILLISECONDS, true)

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = SampleExample_()
    // demo.marbleDiagram()
    demo.emitLast()
}
