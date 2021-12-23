package xyz.incrie.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.core.Incrie;
import xyz.incrie.core.events.IncrieInitializationEvent;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;<init>(Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    private void onGameStarted(CallbackInfo ci) {
        Incrie.Companion.getEventBus().post(new IncrieInitializationEvent(Incrie.Companion.getInstance()));
    }

}