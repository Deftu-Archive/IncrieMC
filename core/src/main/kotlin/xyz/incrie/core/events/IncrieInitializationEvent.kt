package xyz.incrie.core.events

import xyz.deftu.eventbus.Event
import xyz.incrie.core.Incrie

class IncrieInitializationEvent(val incrie: Incrie) : Event()