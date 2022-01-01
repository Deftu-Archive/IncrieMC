package xyz.incrie

import org.apache.logging.log4j.LogManager
import xyz.deftu.eventbus.SimpleEventBus
import xyz.incrie.core.Incrie
import xyz.incrie.core.IncrieInfo
import xyz.incrie.core.events.IncrieInitializationEvent
import xyz.incrie.core.notifications.Notifications

class IncrieImpl : Incrie {

    private val logger = LogManager.getLogger(IncrieInfo.NAME)
    private val eventBus = SimpleEventBus()
    lateinit var notifications: Notifications

    override fun onInitialization(event: IncrieInitializationEvent) {

    }

    override fun logger() = logger
    override fun eventBus() = eventBus
    override fun notifications() = notifications

}