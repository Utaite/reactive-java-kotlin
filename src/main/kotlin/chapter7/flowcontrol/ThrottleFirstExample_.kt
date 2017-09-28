package chapter7.flowcontrol

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class ThrottleFirstExample_ {

    fun marbleDiagram() {
        val data = arrayOf("1", "2", "3", "4", "5", "6")
        CommonUtils_.exampleStart()

        // 앞의 1개는 100ms 간격으로 발행
        val earlySource = Observable.just(data[0])
                .zipWith(Observable.timer(100L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 다음 1개는 300ms 후에 발행
        val middleSource = Observable.just(data[1])
                .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })

        // 마지막 4개는 100ms 후에 발행
        val lateSource = Observable.just(data[2], data[3], data[4], data[5])
                .zipWith(Observable.interval(105L, TimeUnit.MILLISECONDS), BiFunction { a: String, _: Long -> a })
                .doOnNext { Log_.it("doOnNext / $it") }

        // 200ms 간격으로 throttleFirst() 실행함
        val source = Observable.concat<String>(earlySource, middleSource, lateSource)
                .throttleFirst(200L, TimeUnit.MILLISECONDS)

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(2000)
    }

}


fun main(args: Array<String>) {
    val demo = ThrottleFirstExample_()
    demo.marbleDiagram()
}
