package xyz.incrie.gui.notifications

import gg.essential.elementa.components.Window
import gg.essential.elementa.dsl.childOf
import xyz.deftu.deftils.fingerprints.Fingerprints
import xyz.incrie.api.gui.notifications.Notification
import xyz.incrie.api.gui.notifications.Notifications
import java.util.function.Consumer

class NotificationsImpl(private val window: Window) : Notifications {
    override fun post(notification: Notification) {
        notification childOf window
    }
}