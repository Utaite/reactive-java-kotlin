package common

class Log_ {

    companion object {

        fun it(obj: Any) {
            val time = System.currentTimeMillis() - CommonUtils_.startTime
            println(getThreadName() + " | " + time + " | " + "value = " + obj.toString())
        }

        private fun getThreadName(): String =
                Thread.currentThread().name.let {
                    when (it.length > 30) {
                        false -> it
                        true -> it.substring(0, 30) + "..."
                    }
                }

    }

}
