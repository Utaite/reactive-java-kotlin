package chapter8

import common.CommonUtils_
import common.Log_
import io.reactivex.BackpressureOverflowStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class BackPressureExample_ {

    fun makeBackpressure() {
        CommonUtils_.exampleStart()

        val subject = PublishSubject.create<Int>()
        subject.observeOn(Schedulers.computation())
                .subscribe({ data ->
                    CommonUtils_.sleep(100)
                    Log_.it(data)
                }) { err -> Log_.it(err.toString()) }

        for (i in 0..49999999) {
            subject.onNext(i)
        }
        subject.onComplete()
    }

    fun usingBuffer() {
        CommonUtils_.exampleStart()

        Flowable.range(1, 50000000)
                .onBackpressureBuffer(128, { }, BackpressureOverflowStrategy.DROP_LATEST)
                .observeOn(Schedulers.computation())
                .subscribe({ data ->
                    CommonUtils_.sleep(100)
                    Log_.it(data)
                }) { err -> Log_.it(err.toString()) }
    }

    fun usingDrop() {
        CommonUtils_.exampleStart()

        Flowable.range(1, 50000000)
                .onBackpressureDrop()
                .observeOn(Schedulers.computation())
                .subscribe({ data ->
                    CommonUtils_.sleep(100)
                    Log_.it(data)
                }) { err -> Log_.it(err.toString()) }

        CommonUtils_.sleep(20000)
    }

    fun usingLatest() {
        CommonUtils_.exampleStart()

        Flowable.range(1, 50000000)
                .onBackpressureLatest()
                .observeOn(Schedulers.computation())
                .subscribe({ data ->
                    CommonUtils_.sleep(100)
                    Log_.it(data)
                }) { err -> Log_.it(err.toString()) }

        CommonUtils_.sleep(20000)
    }

}


fun main(args: Array<String>) {
    val demo = BackPressureExample_()
    // demo.makeBackpressure()
    // demo.usingBuffer()
    // demo.usingDrop()
    demo.usingLatest()
}
