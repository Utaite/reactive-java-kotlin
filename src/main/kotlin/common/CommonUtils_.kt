package common

import java.io.IOException
import java.net.InetAddress
import java.util.*


class CommonUtils_ {

    companion object {

        val GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master/"

        val ERROR_CODE = "-500"


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


        fun isNetworkAvailable(): Boolean {
            try {
                return InetAddress.getByName("www.google.com").isReachable(1000)
            } catch (e: IOException) {
                Log.it("Network is not available")
            }

            return false
        }

    }

}
