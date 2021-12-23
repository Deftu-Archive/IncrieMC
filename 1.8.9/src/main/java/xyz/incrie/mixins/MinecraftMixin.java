package xyz.incrie.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.eventbus.Event;
import xyz.incrie.core.Incrie;
import xyz.incrie.core.events.IncrieInitializationEvent;

import java.util.Arrays;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;<init>(Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    private void onGameStarted(CallbackInfo ci) {
        System.out.println(IncrieInitializationEvent.class);
        System.out.println(IncrieInitializationEvent.class.getSuperclass());
        System.out.println(Arrays.toString(IncrieInitializationEvent.class.getInterfaces()));
        System.out.println(new IncrieInitializationEvent(Incrie.Companion.getInstance()));
        System.out.println((Event) new IncrieInitializationEvent(Incrie.Companion.getInstance()));
        //Incrie.Companion.getEventBus().post(new IncrieInitializationEvent(Incrie.Companion.getInstance()));
        System.out.println("Wow okay.");
    }

}