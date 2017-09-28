package chapter7

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class DoOnExample_ {

    fun basic() {
        val objs = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*objs)

        source.doOnNext { Log_.it("onNext / $it") }
                .doOnComplete { Log_.it("onComplete") }
                .doOnError { Log_.it("onError / ${it.message}") }
                .subscribe { Log_.it(it) }
    }

    fun withError() {
        val divider = arrayOf(10, 5, 0)

        Observable.fromArray(*divider)
                .map { 1000 / it }
                .doOnNext { Log_.it("onNext / $it") }
                .doOnComplete { Log_.it("onComplete") }
                .doOnError { Log_.it("onError / ${it.message}") }
                .subscribe { Log_.it(it) }
    }

    fun doOnEach() {
        val data = arrayOf("ONE", "TWO", "THREE")
        val source = Observable.fromArray(*data)

        source.doOnEach {
            when {
                it.isOnNext -> Log_.it("onNext / ${it.value}")
                it.isOnComplete -> Log_.it("onComplete")
                it.isOnError -> Log_.it("onError / ${it.error?.message}")
            }
        }.subscribe { Log_.it(it) }
    }

    fun doOnEachObserver() {
        val objs = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*objs)

        source.doOnEach(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                // doOnEach()에서는 onSubscribe() 함수가 호출되지 않습니다.
            }

            override fun onNext(s: String) {
                Log_.it("onNext / $s")
            }

            override fun onError(e: Throwable) {
                Log_.it("onError / ${e.message}")
            }

            override fun onComplete() {
                Log_.it("onComplete")
            }
        }).subscribe { Log_.it(it) }
    }

    fun doOnSubscribeAndDispose() {
        val objs = arrayOf("1", "3", "5", "2", "6")
        val source = Observable.fromArray(*objs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { t1: String, _: Long -> t1 })
                .doOnSubscribe { Log_.it("onSubscribe") }
                .doOnDispose { Log_.it("onDispose") }
        val d = source.subscribe { Log_.it(it) }

        CommonUtils_.sleep(200)
        d.dispose()
        CommonUtils_.sleep(300)
    }

    fun doOnLifeCycle() {
        val objs = arrayOf("1", "3", "5", "2", "6")
        val source = Observable.fromArray(*objs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), BiFunction { t1: String, _: Long -> t1 })
                .doOnLifecycle(
                        { Log_.it("onSubscribe") },
                        { Log_.it("onDispose") }
                )
        val d = source.subscribe { Log_.it(it) }

        CommonUtils_.sleep(200)
        d.dispose()
        CommonUtils_.sleep(300)
    }

    fun doOnTerminate() {
        val objs = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*objs)

        source.doOnTerminate { Log_.it("onTerminate") }
                .doOnComplete { Log_.it("onComplete") }
                .doOnError { Log_.it("onError / ${it.message}") }
                .subscribe { Log_.it(it) }
    }

}


fun main(args: Array<String>) {
    val demo = DoOnExample_()
    // demo.basic()
    // demo.withError()
    // demo.doOnEach()
    // demo.doOnEachObserver()
    // demo.doOnSubscribeAndDispose()
    // demo.doOnLifeCycle()
    demo.doOnTerminate()
}
