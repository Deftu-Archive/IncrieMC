package xyz.incrie

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import xyz.incrie.core.Incrie
import xyz.incrie.core.events.KeyboardInputEvent
import xyz.incrie.core.events.MouseInputEvent
import xyz.incrie.core.events.RenderTickEvent

class IncrieForgeEvents {

    @SubscribeEvent
    private fun onRenderTick(event: TickEvent.RenderTickEvent) {
        Incrie.getEventBus().post(RenderTickEvent())
    }

    @SubscribeEvent
    private fun onKeyInput(event: InputEvent.KeyInputEvent) {
        Incrie.getEventBus().post(KeyboardInputEvent())
    }

    @SubscribeEvent
    private fun onMouseInput(event: InputEvent.MouseInputEvent) {
        Incrie.getEventBus().post(MouseInputEvent())
    }

}