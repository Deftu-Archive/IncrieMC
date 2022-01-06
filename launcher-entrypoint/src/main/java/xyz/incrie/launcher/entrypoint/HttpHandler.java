package xyz.incrie.launcher.entrypoint;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.function.Consumer;

public class HttpHandler {

    public static final OkHttpClient httpClient = new OkHttpClient();

    public static void request(Request request, Consumer<Response> callback) {
        try {
            Response response = httpClient.newCall(request).execute();
            callback.accept(response);
            response.close();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred and a download failed. Please try restarting your game.", e);
        }
    }

}