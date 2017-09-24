package common

import java.util.*


class CommonUtils_ {

    companion object {

        val GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master/"


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


        private val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        fun numberToAlphabet(x: Int): String =
                ALPHABET[x % ALPHABET.length].toString()

    }

}
