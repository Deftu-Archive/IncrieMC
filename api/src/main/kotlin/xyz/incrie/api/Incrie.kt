package xyz.incrie.api

import me.kbrewster.eventbus.EventBus
import me.kbrewster.eventbus.Subscribe
import okhttp3.OkHttpClient
import org.apache.logging.log4j.Logger
import xyz.incrie.api.chat.ChatManager
import xyz.incrie.api.connection.IncrieConnection
import xyz.incrie.api.events.IncrieInitializationEvent
import xyz.incrie.api.events.IncriePostInitializationEvent
import xyz.incrie.api.gui.notifications.Notifications
import xyz.incrie.api.http.HttpRequester
import xyz.incrie.api.utils.JsonHelper
import java.util.*

interface Incrie {

    fun onInitialization(event: IncrieInitializationEvent)

    fun version() = "@VERSION@"
    fun name() = "@NAME@"
    fun id() = "@ID@"

    fun logger(): Logger
    fun eventBus(): EventBus

    fun connection(): IncrieConnection

    fun chatManager(): ChatManager

    fun jsonHelper(): JsonHelper
    fun httpClient(): OkHttpClient
    fun httpRequester(): HttpRequester
    fun notifications(): Notifications

    companion object {
        var initialized = false
            @JvmStatic get
            private set
        lateinit var instance: Incrie
            private set
        private fun instanceOr(): Incrie = if (this::instance.isInitialized) instance else throw IllegalStateException("Incrie has not yet been initialized, please try using Incrie#enqueueInitializationOperation")

        private val initializationOperations = mutableListOf<Runnable>()
        private val postInitializationOperations = mutableListOf<Runnable>()

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

        @Subscribe
        private fun runPostInitialization(event: IncriePostInitializationEvent) {
            for (postInitializationOperation in postInitializationOperations) postInitializationOperation.run()
        }

        @JvmStatic fun enqueueInitializationOperation(operation: Runnable) {
            initializationOperations.add(operation)
        }

        @JvmStatic fun enqueuePostInitialiationOperation(operation: Runnable) {
            postInitializationOperations.add(operation)
        }

        @JvmStatic fun getVersion() = instanceOr().version()
        @JvmStatic fun getName() = instanceOr().name()
        @JvmStatic fun getId() = instanceOr().id()

        @JvmStatic fun getLogger() = instanceOr().logger()
        @JvmStatic fun getEventBus() = instanceOr().eventBus()

        @JvmStatic fun getJsonHelper() = instanceOr().jsonHelper()
        @JvmStatic fun getHttpClient() = instanceOr().httpClient()
        @JvmStatic fun getHttpRequester() = instanceOr().httpRequester()
        @JvmStatic fun getNotifications() = instanceOr().notifications()
    }
}