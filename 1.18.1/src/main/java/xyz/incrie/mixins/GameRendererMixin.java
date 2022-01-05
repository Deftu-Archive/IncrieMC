package xyz.incrie.mixins;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.events.RenderTickEvent;

@Mixin({GameRenderer.class})
public class GameRendererMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void incrie$onRender(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        Incrie.getEventBus().post(new RenderTickEvent());
    }

}