package com.test;

import gg.essential.elementa.components.Window;
import gg.essential.elementa.components.inspector.Inspector;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import xyz.incrie.IncrieImpl;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.gui.notifications.NotificationAlignment;

public class TestMod implements ClientModInitializer {
    public void onInitializeClient() {
        Incrie.enqueuePostInitialiationOperation(() -> {
            Window window = ((IncrieImpl) Incrie.getInstance()).internalHud.getWindow();
            ((IncrieImpl) Incrie.getInstance()).internalHud.getWindow().addChild(new Inspector(window));

            Incrie.getNotifications().post("H6Aus", "19g", NotificationAlignment.BOTTOM, notification -> {
                MinecraftClient.getInstance().setScreen(new TestScreen());
            });
        });
    }
}