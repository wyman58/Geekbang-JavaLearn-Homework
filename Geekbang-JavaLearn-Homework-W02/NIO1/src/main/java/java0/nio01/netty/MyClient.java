package java0.nio01.netty;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MyClient {
    public static void main(String Args[]) throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8801/test")
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
}
