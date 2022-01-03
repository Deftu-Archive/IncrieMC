package xyz.incrie.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.events.MouseButtonEvent;

@Mixin({Mouse.class})
public class MouseMixin {

    @Shadow private double x;
    @Shadow private double y;
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onMouseButton", at = @At(value = "JUMP", ordinal = 18))
    private void incrie$onMouseInput(long window, int button, int action, int mods, CallbackInfo ci) {
        double d = x * (double) client.getWindow().getScaledWidth() / (double) client.getWindow().getWidth();
        double e = y * (double) client.getWindow().getScaledHeight() / (double) client.getWindow().getHeight();
        Incrie.getEventBus().post(new MouseButtonEvent(button, d, e));
    }

}