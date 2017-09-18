package chapter4.combine

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.observables.ConnectableObservable
import java.util.*


class ReactiveSum_ {

    fun run() {
        val source: ConnectableObservable<String> = userInput()
        val a = source.filter { it.startsWith("a:") }
                .map { it.replace("a:", "") }
                .map { it.toInt() }
        val b = source.filter { it.startsWith("b:") }
                .map { it.replace("b:", "") }
                .map { it.toInt() }
        Observable.combineLatest(a.startWith(0), b.startWith(0),
                BiFunction { t1: Int, t2: Int -> t1 + t2 })
                .subscribe { println("Result $it") }
        source.connect()
    }

    fun userInput(): ConnectableObservable<String> =
            Observable.create<String> {
                val scanner = Scanner(System.`in`)
                loop@ while (true) {
                    println("Input: ")
                    val line = scanner.nextLine()
                    it.onNext(line)

                    if (line.contains("exit")) {
                        scanner.close()
                        break@loop
                    }
                }
            }.publish()

}


fun main(args: Array<String>) {
    ReactiveSum_().run()
}
