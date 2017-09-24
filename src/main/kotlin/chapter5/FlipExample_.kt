package chapter5

import common.CommonUtils_
import common.Log_
import common.Shape_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class FlipExample_ {

    fun marbleDiagram() {
        val objs = arrayOf("1-S", "2-T", "3-P")
        val source = Observable.fromArray(*objs)
                .doOnNext { Log_.it("Original data = $it") }
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map { Shape_.flip(it) }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(500)
    }

    fun observeOnRemoved() {
        val objs = arrayOf("1-S", "2-T", "3-P")
        val source = Observable.fromArray(*objs)
                .doOnNext { Log_.it("Original data = $it") }
                .subscribeOn(Schedulers.newThread())
                // .observeOn(Schedulers.newThread())
                .map { Shape_.flip(it) }
        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(500)
    }

}


fun main(args: Array<String>) {
    val demo = FlipExample_()
    demo.marbleDiagram()
    demo.observeOnRemoved()
}
