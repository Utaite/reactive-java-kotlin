package common

import java.util.*


class CommonUtils_ {

    companion object {

        var startTime: Long = 0

        fun exampleStart() {
            startTime = System.currentTimeMillis()
        }

        fun sleep(millis: Int) {
            Thread.sleep(millis.toLong())
        }


        fun doSomething() {
            Thread.sleep(Random().nextInt(100).toLong())
        }

    }

}
