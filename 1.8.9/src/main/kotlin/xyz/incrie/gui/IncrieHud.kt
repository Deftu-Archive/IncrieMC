package xyz.incrie.gui

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.Window
import gg.essential.universal.UMatrixStack
import net.minecraft.client.Minecraft
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse
import xyz.deftu.eventbus.SubscribeEvent
import xyz.incrie.IncrieImpl
import xyz.incrie.core.events.KeyboardInputEvent
import xyz.incrie.core.events.MouseInputEvent
import xyz.incrie.core.events.RenderTickEvent

class IncrieHud(val incrie: IncrieImpl) {
    lateinit var window: Window
        private set

    fun initialize() {
        window = Window(ElementaVersion.V1)
        incrie.eventBus().register(this)
    }

    @SubscribeEvent
    private fun onRenderTick(event: RenderTickEvent) {
        if (Minecraft.getMinecraft().currentScreen == null) {
            window.draw(UMatrixStack.Compat.get())
        }
    }

    @SubscribeEvent
    private fun onKeyboardInput(event: KeyboardInputEvent) {
        window.keyType(
            Keyboard.getEventCharacter(),
            Keyboard.getEventKey()
        )
    }

    @SubscribeEvent
    private fun onMouseInput(event: MouseInputEvent) {
        val scrolled = Mouse.getEventDWheel() != 0
        if (Mouse.getEventButtonState()) {
            if (scrolled) {
                window.mouseScroll(Mouse.getEventDWheel().toDouble().coerceIn(-1.0, 1.0))
            } else {
                window.mouseClick(
                    Mouse.getEventX().toDouble(),
                    Mouse.getEventY().toDouble(),
                    Mouse.getEventButton()
                )
            }
        } else if (!scrolled) {
            window.mouseRelease()
        }
    }
}