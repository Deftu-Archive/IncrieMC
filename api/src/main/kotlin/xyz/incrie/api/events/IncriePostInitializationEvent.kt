package xyz.incrie.api.events

import xyz.incrie.api.Incrie

class IncriePostInitializationEvent : Event(Incrie.instance) {
    companion object {
        @JvmStatic var initialized: Boolean? = null
            set(value) {
                if (field == null) {
                    field = value
                }
            }
    }
}