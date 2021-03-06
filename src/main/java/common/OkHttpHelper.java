package common;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class OkHttpHelper {

    private static OkHttpClient client = new OkHttpClient();

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response res = client.newCall(request).execute();
            return res.body().string();
        } catch (IOException e) {
            Log.it(e);
            throw e;
        }
    }

    public static String getWithLog(String url) throws IOException {
        System.out.println("OkHttp call URL = " + url);
        return get(url);
    }

}
