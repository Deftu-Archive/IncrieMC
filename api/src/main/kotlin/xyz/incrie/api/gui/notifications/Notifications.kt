package xyz.incrie.api.gui.notifications

import xyz.incrie.api.gui.IncrieTheme
import java.util.function.Consumer

interface Notifications {
    fun post(notification: Notification)
    fun post(
        title: String,
        description: String,
        alignment: NotificationAlignment,
        theme: IncrieTheme,
        clickOperation: Consumer<Notification>
    ) = post(Notification(title, description, alignment, theme, clickOperation))
    fun post(
        title: String,
        description: String,
        alignment: NotificationAlignment
    ) = post(title, description, alignment, IncrieTheme.DEFAULT) {}
    fun post(
        title: String,
        description: String,
        theme: IncrieTheme
    ) = post(title, description, NotificationAlignment.BOTTOM_RIGHT) {}
    fun post(
        title: String,
        description: String
    ) = post(title, description, NotificationAlignment.BOTTOM_RIGHT, IncrieTheme.DEFAULT) {}
    fun post(
        title: String,
        description: String,
        clickOperation: Consumer<Notification>
    ) = post(title, description, NotificationAlignment.BOTTOM_RIGHT, IncrieTheme.DEFAULT, clickOperation)
    fun post(
        title: String,
        description: String,
        alignment: NotificationAlignment,
        clickOperation: Consumer<Notification>
    ) = post(title, description, alignment, IncrieTheme.DEFAULT, clickOperation)
    fun post(
        title: String,
        description: String,
        theme: IncrieTheme,
        clickOperation: Consumer<Notification>
    ) = post(title, description, NotificationAlignment.BOTTOM_RIGHT, theme, clickOperation)
}