package common

class Shape_ {

    companion object {

        val HEXAGON = "HEXAGON"
        val OCTAGON = "OCTAGON"
        val RECTANGLE = "RECTANGLE"
        val TRIANGLE = "TRIANGLE"
        val DIAMOND = "DIAMOND"
        val PENTAGON = "PENTAGON"
        val STAR = "STAR"
        val BALL = "BALL"

        private fun getSuffix(shape: String): String =
                when (shape) {
                    HEXAGON -> "-H"
                    OCTAGON -> "-O"
                    RECTANGLE -> "-R"
                    TRIANGLE -> "-T"
                    DIAMOND -> "<>"
                    PENTAGON -> "-P"
                    STAR -> "-S"
                    else -> ""
                }

        fun getString(color: String, shape: String): String =
                color + getSuffix(shape)


    }

}
