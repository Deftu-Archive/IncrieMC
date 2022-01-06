package xyz.incrie.launcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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

    public static void requestJson(Request request, Consumer<JsonElement> callback) {
        request(request, response -> {
            try {
                ResponseBody body = response.body();
                if (body != null) {
                    callback.accept(JsonParser.parseString(body.string()));
                }
            } catch (Exception e) {
                throw new RuntimeException("An error occurred parsing a JSON request. Please try restarting your game.", e);
            }
        });
    }

}