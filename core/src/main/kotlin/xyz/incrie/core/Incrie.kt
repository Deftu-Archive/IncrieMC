package xyz.incrie.core

import org.apache.logging.log4j.Logger
import org.kodein.di.*
import xyz.deftu.eventbus.EventPriority
import xyz.deftu.eventbus.SimpleEventBus
import xyz.deftu.eventbus.SubscribeEvent
import xyz.deftu.eventbus.SubscriberDepth
import xyz.incrie.core.events.IncrieInitializationEvent

interface Incrie {

    fun initialize(event: IncrieInitializationEvent)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun init(event: IncrieInitializationEvent) {
        initialize(event)
    }

    fun logger(): Logger
    fun eventBus(): SimpleEventBus

    companion object {
        var instance: Incrie
            private set

        init {
            val di = IncrieDI(DI.from(emptyList()))
            val directAware = di.direct
            val directDi = directAware.directDI
            instance = directDi.instance(Incrie)
            instance.eventBus().register(instance, SubscriberDepth.SUPER)
        }

        @JvmStatic fun getLogger() = instance.logger()
        @JvmStatic fun getEventBus() = instance.eventBus()
    }
}