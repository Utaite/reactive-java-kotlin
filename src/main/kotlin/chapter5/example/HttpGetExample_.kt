package chapter5.example

import common.Log_
import okhttp3.*
import java.io.IOException


class HttpGetExample_ {

    private val client = OkHttpClient()

    private val URL_README = "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md"

    fun run() {
        val request = Request.Builder()
                .url(URL_README)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log_.it(response.body().string())
            }
        })
    }

}


fun main(args: Array<String>) {
    val demo = HttpGetExample_()
    demo.run()
}
