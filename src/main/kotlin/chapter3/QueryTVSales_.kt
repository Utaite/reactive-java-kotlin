package chapter3

import io.reactivex.Observable


class QueryTVSales_ {

    fun run() {
        val sales: MutableList<Pair<String, Int>> = mutableListOf()

        sales.add("TV" to 2500)
        sales.add("Camera" to 300)
        sales.add("TV" to 1600)
        sales.add("Phone" to 800)

        val tvSales = Observable.fromIterable(sales)
                .filter { "TV" == it.first }
                .map { it.second }
                .reduce { t1, t2 -> t1 + t2 }
        tvSales.subscribe { tot -> println("TV Sales: $$tot") }
    }

    fun run2() {
        val sales: MutableList<Pair<String, Int>> = mutableListOf()

        sales.add("TV" to 2500)
        sales.add("Camera" to 300)
        sales.add("TV" to 1600)
        sales.add("Phone" to 800)

        println("TV Sales: $${getSales(sales)}")
    }

    private fun getSales(sales: MutableList<Pair<String, Int>>): Int =
            sales.filter { "TV" == it.first }
                    .map { it.second }
                    .reduce { t1, t2 -> t1 + t2 }


}

fun main(args: Array<String>) {
    val demo = QueryTVSales_()
    demo.run()
    demo.run2()
}
