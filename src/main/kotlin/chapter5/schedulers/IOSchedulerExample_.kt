package chapter5.schedulers

import common.CommonUtils_
import common.Log_
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File


class IOSchedulerExample_ {

    fun basic() {
        val root = "/Users/n2soft/Desktop"
        val files = File(root).listFiles()
        val source = Observable.fromArray(*files!!)
                .filter { !it.isDirectory }
                .map { it.absolutePath }
                .subscribeOn(Schedulers.io())

        source.subscribe { Log_.it(it) }
        CommonUtils_.sleep(500)
    }

}


fun main(args: Array<String>) {
    val demo = IOSchedulerExample_()
    demo.basic()
}
