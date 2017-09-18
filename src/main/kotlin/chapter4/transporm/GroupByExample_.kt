package chapter4.transporm

import common.Shape_
import io.reactivex.Observable
import io.reactivex.observables.GroupedObservable


class GroupByExample_ {

    fun marbleDiagram() {
        val objs = arrayOf("6", "4", "2-T", "2", "6-T", "4-T")
        val source: Observable<GroupedObservable<String, String>> =
                Observable.fromArray(*objs).groupBy { Shape_.getShape(it) }

        source.subscribe { obj ->
            obj.subscribe { println("Group: ${obj.key}\t Value: $it") }
        }
    }

    fun filterBallGroup() {
        val objs = arrayOf("6", "4", "2-T", "2", "6-T", "4-T")
        val source: Observable<GroupedObservable<String, String>> =
                Observable.fromArray(*objs).groupBy { Shape_.getShape(it) }

        source.subscribe { obj ->
            obj.filter { obj.key == Shape_.BALL }
                    .subscribe { println("Group: ${obj.key}\t Value: $it") }
        }
    }

}


fun main(args: Array<String>) {
    val demo = GroupByExample_()
    demo.marbleDiagram()
    demo.filterBallGroup()
}
