package xyz.incrie.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.eventbus.SimpleEventBus;
import xyz.deftu.eventbus.SubscriberDepth;
import xyz.incrie.core.Incrie;
import xyz.incrie.core.IncrieInfo;
import xyz.incrie.core.events.IncrieInitializationEvent;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;startGame()V", shift = At.Shift.BEFORE))
    private void onGameRan(CallbackInfo ci) {
        Logger logger = LogManager.getLogger(IncrieInfo.NAME);

        logger.warn(Incrie.class.getClassLoader());
        logger.warn(SimpleEventBus.class.getClassLoader());
        
        logger.info("Setting Incrie class-loader.");
        Incrie.setLoader(Launch.classLoader);
        logger.info("Incrie class-loader set.");

        logger.info("Initializing Incrie default service.");
        Incrie.initialize();
        logger.info("Incrie default service initialized.");

        logger.info("Registering Incrie as self-listener...");
        Incrie.getEventBus().register(Incrie.getInstance(), SubscriberDepth.SUPER);
        logger.info("Incrie registered as self-listener!");
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;<init>(Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    private void onGameStarted(CallbackInfo ci) {
        Logger logger = LogManager.getLogger(IncrieInfo.NAME);
        
        logger.info("Posting IncrieInitializationEvent...");
        Incrie.getInstance().initialize(new IncrieInitializationEvent(Incrie.getInstance()));
        //Incrie.getEventBus().post(new IncrieInitializationEvent(Incrie.getInstance()));
        logger.info("Posted IncrieInitializationEvent");
    }

}