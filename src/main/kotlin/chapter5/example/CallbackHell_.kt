package chapter5.example

import common.CommonUtils_
import common.Log_
import okhttp3.*
import java.io.IOException


class CallbackHell_ {

    private val FIRST_URL = "https://api.github.com/zen"
    private val SECOND_URL = CommonUtils_.GITHUB_ROOT + "/samples/callback_hell"

    private val client = OkHttpClient()

    private val onSuccess = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        @Throws(IOException::class)
        override fun onResponse(call: Call, response: Response) {
            Log_.it(response.body().string())
        }
    }


    fun run() {
        CommonUtils_.exampleStart()
        val request = Request.Builder()
                .url(FIRST_URL)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log_.it(response.body().string())

                val request = Request.Builder()
                        .url(SECOND_URL)
                        .build()
                client.newCall(request).enqueue(onSuccess)
            }
        })
    }

}

fun main(args: Array<String>) {
    val demo = CallbackHell_()
    demo.run()
}
