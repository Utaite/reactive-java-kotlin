package common

class CommonUtils_ {

    companion object {

        var startTime: Long = 0

        fun exampleStart() {
            startTime = System.currentTimeMillis()
        }

        fun sleep(millis: Int) {
            Thread.sleep(millis.toLong())
        }

    }

}
