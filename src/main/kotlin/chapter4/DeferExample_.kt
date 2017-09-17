package chapter4

import common.Log_
import common.Shape_
import io.reactivex.Observable


class DeferExample_ {

    val colors: Iterator<String> = listOf("1", "3", "5", "6").iterator()


    fun marbleDiagram() {
        val source = Observable.defer { getObservable() }

        source.subscribe { Log_.it("Subscriber #1 -> $it") }
        source.subscribe { Log_.it("Subscriber #2 -> $it") }
    }

    fun notDeferred() {
        val source = getObservable()
        source.subscribe { Log_.it("Subscriber #1 -> $it") }
        source.subscribe { Log_.it("Subscriber #2 -> $it") }
    }

    private fun getObservable(): Observable<String> =
            when (colors.hasNext()) {
                false -> Observable.empty()
                true -> {
                    val color = colors.next()
                    Observable.just(
                            Shape_.getString(color, Shape_.BALL),
                            Shape_.getString(color, Shape_.RECTANGLE),
                            Shape_.getString(color, Shape_.PENTAGON))
                }
            }

}


fun main(args: Array<String>) {
    val demo = DeferExample_()
    demo.marbleDiagram()
    demo.notDeferred()
}
