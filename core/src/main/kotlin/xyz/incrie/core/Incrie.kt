package xyz.incrie.core

import org.apache.logging.log4j.Logger
import org.kodein.di.*
import xyz.deftu.eventbus.SimpleEventBus

interface Incrie {

    fun getLogger(): Logger
    fun getEventBus(): SimpleEventBus

    companion object {
        var instance: Incrie
            private set

        init {
            val di = IncrieDI(DI.from(emptyList()))
            val directAware = di.direct
            val directDi = directAware.directDI
            instance = directDi.instance()
        }

        fun getLogger() = instance.getLogger()
        fun getEventBus() = instance.getEventBus()
    }
}