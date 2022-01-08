package xyz.incrie.api.events

class MouseButtonEvent(
    val button: Int,
    val released: Boolean,
    val x: Double,
    val y: Double
) : Event()