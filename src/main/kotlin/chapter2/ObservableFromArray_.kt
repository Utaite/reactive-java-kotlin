package chapter2

import io.reactivex.Observable


class ObservableFromArray_ {

    fun integerArray() {
        val arr: Array<Int> = arrayOf(100, 200, 300)
        val source: Observable<Int> = Observable.fromArray(*arr)
        source.subscribe { println(it) }
    }

    fun intArray() {
        val intArray: IntArray = intArrayOf(400, 500, 600)
        // val source = Observable.fromArray<Int>(*intArray.toTypedArray())
        val source = Observable.fromArray<Int>(*intArray.toIntegerArray())
        source.subscribe { println(it) }
    }

}


fun IntArray.toIntegerArray(): Array<Int> {
    val result: Array<Int?> = arrayOfNulls(this.size)
    for(index in IntRange(0, this.lastIndex)) {
        result[index] = this[index]
    }
    return result as Array<Int>
}


fun main(args: Array<String>) {
    val demo = ObservableFromArray_()
    demo.integerArray()
    demo.intArray()
}
