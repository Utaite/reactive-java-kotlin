package chapter7.flowcontrol

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class WindowExample_ {

    fun marbleDiagram() {
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        CommonUtils_.exampleStart()

        // 앞의 3개는 100ms 간격으로 발행
        val earlySource = Observable.fromArray(*data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 가운데 1개는 300ms 후에 발행
        val middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 마지막 2개는 100ms 후에 발행
        val lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 데이터 3개씩 모아서 새로운 Observable을 생성함
        val source = Observable.concat<String>(earlySource, middleSource, lateSource)
                .window(3)

        source.subscribe { observable ->
            Log_.it("New Observable Started!!")
            observable.subscribe { Log_.it(it) }
        }

        CommonUtils_.sleep(1000)
    }

}


fun main(args: Array<String>) {
    val demo = WindowExample_()
    demo.marbleDiagram()
}
