package chapter7

import common.Log_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class ExceptionHandling_ {

    fun cannotCatch() {
        val source = Observable.create<String> {
            it.onNext("1")
            it.onError(Exception("Some Error"))
            it.onNext("3")
            it.onComplete()
        }

        try {
            source.subscribe { Log_.it(it) }
        } catch (e: Exception) {
            Log_.it(e.message ?: "")
        }

    }

    fun onErrorReturn() {
        val grades = arrayOf("70", "88", "$100", "93", "83")

        val source = Observable.fromArray(*grades)
                .map { Integer.parseInt(it) }
                .onErrorReturn { e ->
                    if (e is NumberFormatException) {
                        e.printStackTrace()
                    }
                    -1
                }

        source.subscribe { data ->
            if (data < 0) {
                Log_.it("Wrong Data found!!")
                return@subscribe
            }
            Log_.it("Grade is " + data!!)
        }
    }

    fun onError() {
        val grades = arrayOf("70", "88", "$100", "93", "83")

        val source = Observable.fromArray(*grades)
                .map { Integer.parseInt(it) }
        source.subscribe(
                { Log_.it("Grade is $it") },
                { e ->
                    if (e is NumberFormatException) {
                        e.printStackTrace()
                    }
                    Log_.it("Wrong Data found!!")
                }
        )
    }

    fun onErrorResumeNext() {
        val salesData = arrayOf("100", "200", "A300")
        val onParseError = Observable.defer {
            Log_.it("send email to administrator")
            Observable.just(-1)
        }.subscribeOn(Schedulers.io())

        val source = Observable.fromArray(*salesData)
                .map { it.toInt() }
                .onErrorResumeNext(onParseError)

        source.subscribe { data ->
            if (data < 0) {
                Log_.it("Wrong Data found!!")
                return@subscribe
            }
            Log_.it("Sales data : $data")
        }
    }

}


fun main(args: Array<String>) {
    val demo = ExceptionHandling_()
    // demo.cannotCatch()
    // demo.onErrorReturn()
    // demo.onError()
    demo.onErrorResumeNext()
}
