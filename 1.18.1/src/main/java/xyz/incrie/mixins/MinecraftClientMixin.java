package xyz.incrie.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.events.IncrieInitializationEvent;

@Mixin({MinecraftClient.class})
public class MinecraftClientMixin {

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void incrie$onCreated(RunArgs args, CallbackInfo ci) {
        if (Incrie.initialize()) {
            Incrie.getEventBus().post(new IncrieInitializationEvent());
        }
    }

}