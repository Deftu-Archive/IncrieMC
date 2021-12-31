package com.test;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import xyz.deftu.eventbus.SubscribeEvent;
import xyz.incrie.core.Incrie;
import xyz.incrie.core.gui.IncrieTheme;
import xyz.incrie.core.gui.IncrieThemeBuilder;

import java.awt.*;

@Mod(
        name = "Test",
        modid = "test",
        version = "1.0.0"
)
public class Test {

    public static final IncrieTheme THEME = new IncrieThemeBuilder()
            .setPrimary(new Color(0, 226, 255))
            .setSecondary(new Color(37, 37, 37))
            .setTertiary(new Color(72, 255, 0))
            .build();

    @Mod.EventHandler
    private void onInitialization(FMLInitializationEvent event) {
        Incrie.getEventBus().register(this);
    }

/*    @SubscribeEvent
    private void onChatSent(ChatMessageSentEvent event) {
        if (event.message.startsWith(".")) {
            if (event.message.substring(1).equals("notify")) {
                Incrie.getNotifications().post("Title", "Description", THEME);
                Incrie.getNotifications().post("Title 2", "Description 2");
                Incrie.getNotifications().post("Title 3", "Description 3", NotificationAlignment.BOTTOM_LEFT);
                Incrie.getNotifications().post("Title 4", "Description 4", NotificationAlignment.TOP_RIGHT, THEME);
            }
        }
    }*/

}