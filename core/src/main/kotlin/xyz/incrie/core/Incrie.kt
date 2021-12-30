package xyz.incrie.core

import org.apache.logging.log4j.Logger
import xyz.deftu.eventbus.*
import xyz.incrie.core.events.IncrieInitializationEvent
import xyz.incrie.core.notifications.Notifications
import java.util.*

interface Incrie {

    fun onInitialization(event: IncrieInitializationEvent)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun initialize(event: IncrieInitializationEvent) {
        onInitialization(event)
    }

    fun logger(): Logger

    fun eventBus(): SimpleEventBus
    fun notifications(): Notifications

    companion object {
        var loader: ClassLoader? = null
            @JvmStatic set(value) {
                if (field != null)
                    throw IllegalStateException("Cannot set loader after it has already been set.")
                field = value
            }
        lateinit var instance: Incrie
            @JvmStatic get
            private set

        @JvmStatic fun initialize() {
            val service = ServiceLoader.load(Incrie::class.java, loader)
            val iterator = service.iterator()
            if (iterator.hasNext()) {
                instance = iterator.next()
                if (iterator.hasNext()) {
                    throw IllegalStateException("There is more than one implementation of Incrie, this is not supported.")
                }
            } else {
                throw IllegalStateException("Failed to find implementation of Incrie.")
            }

            instance.eventBus().register(instance, SubscriberDepth.SUPER)
        }

        @JvmStatic fun getLogger() = instance.logger()

        @JvmStatic fun getEventBus() = instance.eventBus()
        @JvmStatic fun getNotifications() = instance.notifications()
    }
}