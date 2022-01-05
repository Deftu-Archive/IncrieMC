package xyz.incrie.gui

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.Window
import gg.essential.universal.UMatrixStack
import me.kbrewster.eventbus.Subscribe
import xyz.incrie.api.Incrie
import xyz.incrie.api.events.MouseButtonEvent
import xyz.incrie.api.events.MouseRepositionEvent
import xyz.incrie.api.events.MouseScrollEvent
import xyz.incrie.api.events.RenderTickEvent

class IncrieInternalHud(val incrie: Incrie) {
    lateinit var window: Window
        private set

    fun initialize() {
        window = Window(ElementaVersion.V1)
        incrie.eventBus().register(this)
    }

    @Subscribe
    private fun onRenderTick(event: RenderTickEvent) {
        window.draw(UMatrixStack())
    }

    @Subscribe
    private fun onMouseInput(event: MouseButtonEvent) {
        if (!event.released) {
            window.mouseClick(event.x, event.y, event.button)
        } else {
            window.mouseRelease()
        }
    }

    @Subscribe
    private fun onMouseScroll(event: MouseScrollEvent) {
        window.mouseScroll(event.delta)
    }

    @Subscribe
    private fun onMouseReposition(event: MouseRepositionEvent) {
        window.mouseMove(window)
    }
}