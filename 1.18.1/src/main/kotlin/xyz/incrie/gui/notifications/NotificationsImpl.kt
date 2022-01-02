package xyz.incrie.gui.notifications

import gg.essential.elementa.components.Window
import gg.essential.elementa.dsl.childOf
import xyz.incrie.api.gui.notifications.Notification
import xyz.incrie.api.gui.notifications.Notifications

class NotificationsImpl(val window: Window) : Notifications {
    override fun post(notification: Notification) {
        notification childOf window
        notification.animateIn()
    }
}