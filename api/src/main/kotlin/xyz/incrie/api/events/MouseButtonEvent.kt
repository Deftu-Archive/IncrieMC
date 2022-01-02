package xyz.incrie.api.events

import xyz.incrie.api.Incrie

class MouseButtonEvent(
    val button: Int,
    val x: Double,
    val y: Double
) : Event(Incrie.instance)