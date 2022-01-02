package xyz.incrie.api

import me.kbrewster.eventbus.EventBus
import org.apache.logging.log4j.Logger
import xyz.incrie.api.events.IncrieInitializationEvent
import xyz.incrie.api.gui.notifications.Notifications
import java.util.*

interface Incrie {

    fun onInitialization(event: IncrieInitializationEvent)

    fun logger(): Logger
    fun eventBus(): EventBus

    fun notifications(): Notifications

    companion object {
        lateinit var instance: Incrie
            @JvmStatic get
            private set

        @JvmStatic fun initialize() {
            val service = ServiceLoader.load(Incrie::class.java)
            val iterator = service.iterator()
            if (iterator.hasNext()) {
                instance = iterator.next()
                if (iterator.hasNext()) {
                    throw IllegalStateException("There is more than one implementation of Incrie, this is not supported.")
                }
            } else {
                throw IllegalStateException("Failed to find implementation of Incrie.")
            }

            instance.eventBus().register(instance)
        }

        @JvmStatic fun getLogger() = instance.logger()

        @JvmStatic fun getEventBus() = instance.eventBus()
        @JvmStatic fun getNotifications() = instance.notifications()
    }
}