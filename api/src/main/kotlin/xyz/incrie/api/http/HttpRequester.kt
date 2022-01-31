package xyz.incrie.api.http

import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import xyz.incrie.api.Incrie
import java.util.function.Consumer

class HttpRequester(
    val httpClient: OkHttpClient
) {
    fun request(request: Request, callback: Consumer<Response>) {
        val response = httpClient.newCall(request).execute()
        callback.accept(response)
        response.close()
    }

    fun requestJson(request: Request, callback: Consumer<JsonElement>) {
        request(request) {
            it.body?.let {
                callback.accept(Incrie.getJsonHelper().parse(it.string()))
            }
        }
    }
}