package xyz.incrie.api

import me.kbrewster.eventbus.EventBus
import me.kbrewster.eventbus.Subscribe
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
        private var initialized = false
        lateinit var instance: Incrie
            @JvmStatic get
            private set
        fun instanceOr(): Incrie = if (this::instance.isInitialized) instance else throw IllegalStateException("Incrie has not yet been initialized, please try using Incrie#enqueueInitializationOperation")
        private val initializationOperations = mutableListOf<Runnable>()

        @JvmStatic fun initialize(): Boolean {
            if (!initialized) {
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

                instance.eventBus().register(this)
                initialized = true
                return true
            } else return false
        }

        @Subscribe
        private fun runInitialization(event: IncrieInitializationEvent) {
            instance.onInitialization(event)
            for (initializationOperation in initializationOperations) initializationOperation.run()
        }

        @JvmStatic fun enqueueInitializationOperation(operation: Runnable) {
            initializationOperations.add(operation)
        }

        @JvmStatic fun getLogger() = instanceOr().logger()

        @JvmStatic fun getEventBus() = instanceOr().eventBus()
        @JvmStatic fun getNotifications() = instanceOr().notifications()
    }
}