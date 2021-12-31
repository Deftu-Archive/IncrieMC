package xyz.incrie.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.core.Incrie;
import xyz.incrie.core.IncrieInfo;
import xyz.incrie.core.events.IncrieInitializationEvent;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Unique private final Logger incrieLogger = LogManager.getLogger(IncrieInfo.NAME);

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;<init>(Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    private void onGameStarted(CallbackInfo ci) {
        incrieLogger.debug("Setting Incrie class-loader.");
        Incrie.setLoader(Launch.classLoader);
        incrieLogger.debug("Incrie class-loader set.");

        incrieLogger.debug("Initializing Incrie default service.");
        Incrie.initialize();
        incrieLogger.debug("Incrie default service initialized.");

        incrieLogger.debug("Posting IncrieInitializationEvent...");
        Incrie.getInstance().initialize(new IncrieInitializationEvent(Incrie.getInstance()));
        //Incrie.getEventBus().post(new IncrieInitializationEvent(Incrie.getInstance()));
        incrieLogger.debug("Posted IncrieInitializationEvent");
    }

}