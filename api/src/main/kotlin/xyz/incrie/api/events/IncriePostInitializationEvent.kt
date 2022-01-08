package xyz.incrie.api.events

class IncriePostInitializationEvent : Event() {
    companion object {
        @JvmStatic var initialized: Boolean? = null
            set(value) {
                if (field == null) {
                    field = value
                }
            }
    }
}