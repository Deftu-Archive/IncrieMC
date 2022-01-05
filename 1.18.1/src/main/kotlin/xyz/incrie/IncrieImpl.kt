package xyz.incrie

import me.kbrewster.eventbus.*
import me.kbrewster.eventbus.invokers.ReflectionInvoker
import okhttp3.OkHttpClient
import org.apache.logging.log4j.LogManager
import xyz.incrie.api.Incrie
import xyz.incrie.api.IncrieInfo
import xyz.incrie.api.events.IncrieInitializationEvent
import xyz.incrie.api.gui.notifications.Notifications
import xyz.incrie.api.http.HttpRequester
import xyz.incrie.api.utils.JsonHelper
import xyz.incrie.gui.IncrieInternalHud
import xyz.incrie.gui.notifications.NotificationsImpl
import xyz.incrie.utils.JsonHelperImpl

class IncrieImpl : Incrie {

    private val logger = LogManager.getLogger(IncrieInfo.NAME)
    private val eventBus = eventbus {
        invoker { ReflectionInvoker() }
        exceptionHandler { e -> logger.error("An unexpected error occurred.", e) }
    }

    lateinit var internalHud: IncrieInternalHud
    lateinit var jsonHelper: JsonHelper
    lateinit var httpClient: OkHttpClient
    lateinit var httpRequester: HttpRequester
    lateinit var notifications: Notifications

    override fun onInitialization(event: IncrieInitializationEvent) {
        internalHud = IncrieInternalHud(this).also { it.initialize() }
        jsonHelper = JsonHelperImpl()
        httpClient = OkHttpClient()
        httpRequester = HttpRequester(httpClient)
        notifications = NotificationsImpl(internalHud.window).also { it.initialize() }
    }

    override fun logger() = logger
    override fun eventBus() = eventBus

    override fun jsonHelper() = jsonHelper
    override fun httpClient() = httpClient
    override fun httpRequester() = httpRequester
    override fun notifications() = notifications

}