package xyz.incrie.notifications

import gg.essential.elementa.dsl.*
import xyz.incrie.IncrieImpl
import xyz.incrie.core.notifications.Notification
import xyz.incrie.core.notifications.Notifications
import java.util.concurrent.ConcurrentLinkedQueue

class NotificationsImpl(val incrie: IncrieImpl) : Notifications {
    private val notifications = ConcurrentLinkedQueue<Notification>()
    override fun post(notification: Notification) {
        notifications.add(notification)
        notification childOf incrie.internalHud.window
        notification.animateIn()
    }
}