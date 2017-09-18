package common

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class OkHttpHelper_ {

    companion object {

        private val client by lazy { OkHttpClient() }

        @Throws(IOException::class)
        fun get(url: String): String {
            val request = Request.Builder()
                    .url(url)
                    .build()

            try {
                val res = client.newCall(request).execute()
                return res.body().string()
            } catch (e: IOException) {
                Log.it(e)
                throw e
            }

        }

    }

}
