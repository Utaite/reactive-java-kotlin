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
        val NO_SHAPE = "NO_SHAPE"


        fun getColor(shape: String): String =
                when (shape.endsWith("<>")) {
                    true -> shape.replace("<>", "").trim()
                    false -> when (shape.indexOf("-") > 0) {
                        true -> shape.substring(0, shape.indexOf("-"))
                        false -> shape
                    }
                }

        fun getSuffix(shape: String): String =
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

        fun getShape(obj: String): String =
                when {
                    obj.isEmpty() -> NO_SHAPE
                    obj.endsWith("-H") -> HEXAGON
                    obj.endsWith("-O") -> OCTAGON
                    obj.endsWith("-R") -> RECTANGLE
                    obj.endsWith("-T") -> TRIANGLE
                    obj.endsWith("<>") -> DIAMOND
                    obj.endsWith("-P") -> PENTAGON
                    obj.endsWith("-S") -> STAR
                    else -> "BALL"
                }

        fun getString(color: String, shape: String): String =
                color + getSuffix(shape)


    }

}
