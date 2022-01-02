package xyz.incrie.api.events

import xyz.incrie.api.Incrie

abstract class Event @JvmOverloads constructor(val incrie: Incrie, var cancelled: Boolean = false)