package xyz.incrie

import me.kbrewster.eventbus.Subscribe
import me.kbrewster.eventbus.*
import me.kbrewster.eventbus.invokers.ReflectionInvoker
import org.apache.logging.log4j.LogManager
import xyz.incrie.api.Incrie
import xyz.incrie.api.IncrieInfo
import xyz.incrie.api.events.IncrieInitializationEvent
import xyz.incrie.api.gui.notifications.Notifications
import xyz.incrie.gui.IncrieInternalHud
import xyz.incrie.gui.notifications.NotificationsImpl

class IncrieImpl : Incrie {

    private val logger = LogManager.getLogger(IncrieInfo.NAME)
    private val eventBus = eventbus {
        invoker { ReflectionInvoker() }
        exceptionHandler { e -> logger.error("An unexpected error occurred.", e) }
    }

    lateinit var internalHud: IncrieInternalHud
    lateinit var notifications: Notifications

    @Subscribe
    override fun onInitialization(event: IncrieInitializationEvent) {
        internalHud = IncrieInternalHud(this).also { it.initialize() }
        notifications = NotificationsImpl(internalHud.window)
    }

    override fun logger() = logger
    override fun eventBus() = eventBus
    override fun notifications() = notifications

}