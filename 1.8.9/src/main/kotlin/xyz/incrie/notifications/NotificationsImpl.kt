package xyz.incrie.notifications

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import xyz.incrie.core.notifications.Notification
import xyz.incrie.core.notifications.Notifications
import java.util.concurrent.ConcurrentLinkedQueue

class NotificationsImpl : Notifications {
    private val notifications = ConcurrentLinkedQueue<Notification>()

    override fun post(notification: Notification) {
        notifications.add(notification)
    }

    @SubscribeEvent
    private fun onRenderTick(event: TickEvent.RenderTickEvent) {
        val resolution = ScaledResolution(Minecraft.getMinecraft())
        val scaledWidth = resolution.scaledWidth
        val scaledHeight = resolution.scaledHeight

        var yOff = 5
        for (notification in notifications) {
            if (notifications.indexOf(notification) > 2) continue
        }
    }
}