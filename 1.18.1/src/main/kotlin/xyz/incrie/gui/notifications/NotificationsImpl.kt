package xyz.incrie.gui.notifications

import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.Window
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.dsl.*
import gg.essential.elementa.utils.ObservableRemoveEvent
import xyz.incrie.api.gui.notifications.Notification
import xyz.incrie.api.gui.notifications.NotificationAlignment
import xyz.incrie.api.gui.notifications.Notifications
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

class NotificationsImpl(private val window: Window) : Notifications {
    private val container = UIContainer()
    private val queue = mutableMapOf<NotificationAlignment, Queue<Notification>>()

    override fun initialize() {
        container.constrain {
            width = RelativeConstraint()
            height = RelativeConstraint()
        } childOf window
        for (value in NotificationAlignment.values()) {
            queue[value] = ConcurrentLinkedDeque()
        }

        container.children.addObserver { _, event ->
            if (event is ObservableRemoveEvent<*>) {
                if (event.element.value != null && event.element.value is Notification) {
                    val queued = queue[(event.element.value as Notification).alignment]!!.poll()
                    if (queued != null) post(queued)
                }
            }
        }
    }

    override fun post(notification: Notification) {
        if (container.childrenOfType(Notification::class.java).isEmpty()) {
            notification childOf container
        } else {
            queue[notification.alignment]!!.add(notification)
        }
    }
}