package xyz.incrie.mixins;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.events.IncriePostInitializationEvent;

@Mixin({TitleScreen.class})
public class TitleScreenMixin {

    @Inject(method = "init", at = @At("TAIL"))
    private void incrie$onPostInitialize(CallbackInfo ci) {
        if (Incrie.getInitialized() && (IncriePostInitializationEvent.getInitialized() == null || IncriePostInitializationEvent.getInitialized() == Boolean.FALSE)) {
            Incrie.getEventBus().post(new IncriePostInitializationEvent());
            IncriePostInitializationEvent.setInitialized(true);
        }
    }

}