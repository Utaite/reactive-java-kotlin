package chapter4.transporm

import common.Log_
import io.reactivex.Observable

class ScanExample_ {

    fun marbleDiagram() {
        val balls = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*balls)
                .scan { ball1, ball2 -> "$ball2($ball1)" }
        source.subscribe { Log_.it(it) }
    }

}


fun main(args: Array<String>) {
    val demo = ScanExample_()
    demo.marbleDiagram()
}
