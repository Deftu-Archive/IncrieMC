package xyz.incrie.api.events

import xyz.incrie.api.Incrie

class MouseScrollEvent(
    val delta: Double
) : Event(Incrie.instance)