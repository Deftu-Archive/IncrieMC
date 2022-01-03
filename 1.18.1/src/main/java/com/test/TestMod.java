package com.test;

import net.fabricmc.api.ClientModInitializer;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.gui.notifications.NotificationAlignment;

public class TestMod implements ClientModInitializer {
    public void onInitializeClient() {
        Incrie.enqueueInitializationOperation(() -> {

            Incrie.getNotifications().post("H6Aus", "19g", NotificationAlignment.BOTTOM, notification -> {
                System.out.println("I WAS CLICKED! OH GOD THE HUMANITY!");
            });

            Incrie.getNotifications().post("Bidd", "6t3", NotificationAlignment.BOTTOM, notification -> {
                System.out.println("NOOOOOOOOOOO!");
            });

            Incrie.getNotifications().post("XBD2p", "7pz6lwn", NotificationAlignment.BOTTOM, notification -> {
                System.out.println("SDFGHJGFDGHFDGF!");
            });

            Incrie.getNotifications().post("YzURy", "63dJ", NotificationAlignment.BOTTOM, notification -> {
                System.out.println("DSFGHJKHSDR!");
            });

            Incrie.getNotifications().post("O5TAntW6", "2yS1rtS", NotificationAlignment.BOTTOM, notification -> {
                System.out.println("NOOOOOOOOOOO!");
            });

        });
    }
}