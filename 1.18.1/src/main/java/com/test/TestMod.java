package com.test;

import net.fabricmc.api.ClientModInitializer;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.gui.notifications.NotificationAlignment;

public class TestMod implements ClientModInitializer {

    public void onInitializeClient() {
        Incrie.enqueueInitializationOperation(() -> {
            Incrie.getNotifications().post("Hello", "World", NotificationAlignment.BOTTOM, notification -> {
                Incrie.getNotifications().post("Hello 2", "World 2", NotificationAlignment.TOP, notification1 -> {
                    Incrie.getNotifications().post("Hello 3", "World 3");
                });
            });
        });
    }

}