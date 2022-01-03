package xyz.incrie.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.events.IncrieInitializationEvent;
import xyz.incrie.api.events.RenderTickEvent;

@Mixin({MinecraftClient.class})
public class MinecraftClientMixin {

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SplashOverlay;init(Lnet/minecraft/client/MinecraftClient;)V"))
    private void incrie$onCreated(RunArgs args, CallbackInfo ci) {
        Incrie.initialize();
        Incrie.getEventBus().post(new IncrieInitializationEvent());
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;draw(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
    private void incrie$onRender(boolean tick, CallbackInfo ci) {
        Incrie.getEventBus().post(new RenderTickEvent());
    }

}