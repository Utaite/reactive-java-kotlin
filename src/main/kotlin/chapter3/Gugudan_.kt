package chapter3

import io.reactivex.Observable
import java.util.*


class Gugudan_ {

    fun plainKotlin() {
        val data = Scanner(System.`in`)
        println("Gugudan Input:")
        val dan = data.nextLine().toInt()
        for (row in 1..9) {
            println(dan.toString() + " * " + row + " = " + dan * row)
        }
    }


    fun reactiveV1() {
        val data = Scanner(System.`in`)
        println("Gugudan Input:")
        val dan = data.nextLine().toInt()

        val source = Observable.range(1, 9)
        source.subscribe { row -> println(dan.toString() + " * " + row + " = " + dan * row) }
    }


    fun reactiveV2() {
        val data = Scanner(System.`in`)
        println("Gugudan Input:")
        val dan = data.nextLine().toInt()

        val gugudan = { num: Int ->
            Observable.range(1, 9).map<String> { row ->
                num.toString() + " * " + row + " = " + dan * row
            }
        }
        val source = Observable.just(dan).flatMap(gugudan)
        source.subscribe { println(it) }
    }

    fun reactiveV3() {
        val data = Scanner(System.`in`)
        println("Gugudan Input:")
        val dan = data.nextLine().toInt()

        val source = Observable.just(dan)
                .flatMap { num ->
                    Observable
                            .range(1, 9)
                            .map { num.toString() + " * " + it + " = " + dan * it }
                }
        source.subscribe { println(it) }
    }

    fun usingResultSelector() {
        val data = Scanner(System.`in`)
        println("Gugudan Input:")
        val dan = data.nextLine().toInt()

        val source = Observable.just(dan)
                .flatMap({
                    Observable.range(1, 9)
                }, { num, row ->
                    num.toString() + " * " + row + " = " + num * row
                })
        source.subscribe { println(it) }
    }

}


fun main(args: Array<String>) {
    val demo = Gugudan_()
    // demo.plainKotlin()
    // demo.reactiveV1()
    // demo.reactiveV2()
    // demo.reactiveV3()
    demo.usingResultSelector()
}