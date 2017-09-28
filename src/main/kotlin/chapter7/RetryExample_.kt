package chapter7

import common.CommonUtils_
import common.Log_
import common.OkHttpHelper_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RetryExample_ {

    fun retry5() {
        CommonUtils_.exampleStart()

        val url = "https://api.github.com/zen"
        val source = Observable.just(url)
                .map { OkHttpHelper_.get(it) }
                .retry(5)
                .onErrorReturn { CommonUtils_.ERROR_CODE }

        source.subscribe { Log_.it("result : $it") }
    }

    fun retryWithDelay() {
        val RETRY_MAX = 5
        val RETRY_DELAY = 1000

        CommonUtils_.exampleStart()

        val url = " https://api.github.com/zen"
        val source = Observable.just(url)
                .map { OkHttpHelper_.get(it) }
                .retry { retryCnt, _ ->
                    Log_.it("retryCnt = $retryCnt")
                    CommonUtils_.sleep(RETRY_DELAY)
                    retryCnt < RETRY_MAX
                }
                .onErrorReturn { CommonUtils_.ERROR_CODE }

        source.subscribe { Log_.it("result : $it") }
    }

    fun retryUntil() {
        CommonUtils_.exampleStart()

        val url = "https://api.github.com/zen"
        val source = Observable.just(url)
                .map { OkHttpHelper_.get(it) }
                .subscribeOn(Schedulers.io())
                .retryUntil {
                    if (CommonUtils_.isNetworkAvailable()) {
                        true
                    }
                    CommonUtils_.sleep(1000)
                    false
                }
        source.subscribe { Log_.it(it) }

        CommonUtils_.sleep(5000)
    }

    fun retryWhen() {
        Observable.create<String> {
            it.onError(RuntimeException("always fails"))
        }.retryWhen { attempts ->
            attempts.zipWith(Observable.range(1, 3), BiFunction { _: Throwable, i: Int -> i })
                    .flatMap({ i ->
                        Log_.it("delay retry by $i seconds")
                        Observable.timer(1, TimeUnit.SECONDS)
                    })
        }.blockingForEach { Log_.it(it) }
    }

}


fun main(args: Array<String>) {
    val demo = RetryExample_()
    // demo.retry5()
    // demo.retryWithDelay()
    // demo.retryUntil()
    demo.retryWhen()
}
