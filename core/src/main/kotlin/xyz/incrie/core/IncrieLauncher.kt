package xyz.incrie.core

import org.spongepowered.asm.launch.MixinBootstrap
import org.spongepowered.asm.mixin.Mixins

object IncrieLauncher {
    var initialized = false
        private set
    @JvmStatic fun initialize() {
        if (!initialized) {
            initialized = true
            MixinBootstrap.init()
            Mixins.addConfiguration("mixins.incrie.json")
        }
    }
}