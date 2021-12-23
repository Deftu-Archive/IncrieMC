package xyz.incrie.core.events;

import xyz.deftu.eventbus.Event;
import xyz.incrie.core.Incrie;

public class IncrieInitializationEvent extends Event {
    public final Incrie incrie;
    public IncrieInitializationEvent(Incrie incrie) {
        this.incrie = incrie;
    }
}