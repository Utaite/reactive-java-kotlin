package chapter5.example

import common.CommonUtils_
import common.Log_
import common.OkHttpHelper_
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers


class CallbackHeaven_ {

    private val FIRST_URL = "https://api.github.com/zen"
    private val SECOND_URL = CommonUtils_.GITHUB_ROOT + "/samples/callback_heaven"


    fun usingConcat() {
        CommonUtils_.exampleStart()
        val source = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map { OkHttpHelper_.get(it) }
                .concatWith(Observable.just(SECOND_URL)
                        .map { OkHttpHelper_.get(it) })
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(5000)
    }

    fun usingZip() {
        CommonUtils_.exampleStart()
        val first = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map { OkHttpHelper_.get(it) }
        val second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map { OkHttpHelper_.get(it) }

        Observable.zip(first, second, BiFunction { a: String, b: String -> "\n>> $a\n>> $b" })
                .subscribe { Log_.it(it) }

        CommonUtils_.sleep(5000)
    }

}


fun main(args: Array<String>) {
    val demo = CallbackHeaven_()
    // demo.usingConcat()
    demo.usingZip()
}
