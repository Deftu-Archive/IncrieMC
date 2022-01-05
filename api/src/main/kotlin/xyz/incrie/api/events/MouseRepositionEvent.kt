package xyz.incrie.api.events

import xyz.incrie.api.Incrie

class MouseRepositionEvent(
    val x: Double,
    val y: Double
) : Event(Incrie.instance)