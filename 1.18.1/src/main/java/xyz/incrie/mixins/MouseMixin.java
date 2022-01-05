package xyz.incrie.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.events.MouseButtonEvent;
import xyz.incrie.api.events.MouseRepositionEvent;
import xyz.incrie.api.events.MouseScrollEvent;

@Mixin({Mouse.class})
public class MouseMixin {

    @Shadow private double x;
    @Shadow private double y;
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onMouseButton", at = @At(value = "JUMP", ordinal = 18), cancellable = true)
    private void incrie$onMouseInput(long window, int button, int action, int mods, CallbackInfo ci) {
        double d = x * (double) client.getWindow().getScaledWidth() / (double) client.getWindow().getWidth();
        double e = y * (double) client.getWindow().getScaledHeight() / (double) client.getWindow().getHeight();

        MouseButtonEvent event = new MouseButtonEvent(
                button, /* The button clicked */
                action == GLFW.GLFW_MOUSE_BUTTON_2, /* The state of the mouse button, GLFW_MOUSE_BUTTON_2 is the equivalent of it being released for this input action. */
                d, /* The X location of the mouse pointer. */
                e/* The Y location of the mouse pointer. */
        );

        Incrie.getEventBus().post(event);

        if (event.getCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "onMouseScroll", at = @At(value = "JUMP", ordinal = 3), cancellable = true)
    private void incrie$onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        double d = (this.client.options.discreteMouseScroll ? Math.signum(vertical) : vertical) * this.client.options.mouseWheelSensitivity;

        MouseScrollEvent event = new MouseScrollEvent(d);
        Incrie.getEventBus().post(event);
        if (event.getCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "onCursorPos", at = @At(value = "JUMP", ordinal = 2), cancellable = true)
    private void incrie$onCursorPos(long window, double x, double y, CallbackInfo ci) {
        double d = x * (double) client.getWindow().getScaledWidth() / (double) client.getWindow().getWidth();
        double e = y * (double) client.getWindow().getScaledHeight() / (double) client.getWindow().getHeight();

        Incrie.getEventBus().post(new MouseRepositionEvent(d, e));
    }

}