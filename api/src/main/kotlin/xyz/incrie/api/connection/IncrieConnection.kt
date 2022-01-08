package xyz.incrie.api.connection

import org.apache.logging.log4j.LogManager
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import xyz.incrie.api.Incrie
import xyz.incrie.api.text.Text
import java.lang.Exception
import java.lang.RuntimeException
import java.net.URI
import java.util.concurrent.TimeUnit

class IncrieConnection(
    private val incrie: Incrie,
    uri: String
) : WebSocketClient(
    URI.create(uri)
) {
    private val logger = LogManager.getLogger("${incrie.name()} (${this::class.java.simpleName})")

    fun awaitConnect(): Boolean {
        try {
            return connectBlocking(10, TimeUnit.SECONDS)
        } catch (e: Exception) {
            throw RuntimeException("An error occurred connecting to the Incrie server.", e)
        }
    }

    override fun onOpen(handshakedata: ServerHandshake) {
        logger.info("Connection to the Incrie server has been opened.")
    }

    override fun onMessage(message: String) {
        println(message)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        logger.warn("Connection to the Incrie server has been closed. (Code: $code" +
                "\nReason: $reason" +
                "\nRemote: $remote)")
    }

    override fun onError(ex: Exception) {
        incrie.chatManager().send(Text.of("The Incrie connection encountered an error, check game logs for more information."))
        logger.error("An unexpected error occurred.", ex)
    }
}