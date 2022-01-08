package xyz.incrie

import me.kbrewster.eventbus.*
import me.kbrewster.eventbus.invokers.ReflectionInvoker
import okhttp3.OkHttpClient
import org.apache.logging.log4j.LogManager
import xyz.incrie.api.Incrie
import xyz.incrie.api.chat.ChatManager
import xyz.incrie.api.connection.IncrieConnection
import xyz.incrie.api.events.IncrieInitializationEvent
import xyz.incrie.api.gui.notifications.Notifications
import xyz.incrie.api.http.HttpRequester
import xyz.incrie.api.utils.JsonHelper
import xyz.incrie.chat.ChatManagerImpl
import xyz.incrie.gui.IncrieInternalHud
import xyz.incrie.gui.notifications.NotificationsImpl
import xyz.incrie.utils.JsonHelperImpl

class IncrieImpl : Incrie {

    private val logger = LogManager.getLogger(name())
    private val eventBus = eventbus {
        invoker { ReflectionInvoker() }
        exceptionHandler { e -> logger.error("An unexpected error occurred.", e) }
    }

    lateinit var connection: IncrieConnection

    lateinit var chatManager: ChatManager

    lateinit var internalHud: IncrieInternalHud
    lateinit var jsonHelper: JsonHelper
    lateinit var httpClient: OkHttpClient
    lateinit var httpRequester: HttpRequester
    lateinit var notifications: Notifications

    override fun onInitialization(event: IncrieInitializationEvent) {
        connection = IncrieConnection(this, "").also { it.awaitConnect() }

        chatManager = ChatManagerImpl()

        internalHud = IncrieInternalHud(this).also { it.initialize() }
        jsonHelper = JsonHelperImpl()
        httpClient = OkHttpClient()
        httpRequester = HttpRequester(httpClient)
        notifications = NotificationsImpl(internalHud.window).also { it.initialize() }
    }

    override fun logger() = logger
    override fun eventBus() = eventBus

    override fun connection() = connection

    override fun chatManager() = chatManager

    override fun jsonHelper() = jsonHelper
    override fun httpClient() = httpClient
    override fun httpRequester() = httpRequester
    override fun notifications() = notifications

}