package xyz.incrie.core.notifications

import xyz.incrie.core.gui.IncrieTheme

interface Notifications {
    fun post(notification: Notification)
    fun post(
        title: String,
        description: String,
        alignment: NotificationAlignment,
        theme: IncrieTheme
    ) = post(Notification(title, description, alignment, theme))
    fun post(
        title: String,
        description: String,
        alignment: NotificationAlignment
    ) = post(title, description, alignment, IncrieTheme.DEFAULT)
    fun post(
        title: String,
        description: String,
        theme: IncrieTheme
    ) = post(title, description, NotificationAlignment.BOTTOM_RIGHT)
    fun post(
        title: String,
        description: String
    ) = post(title, description, NotificationAlignment.BOTTOM_RIGHT, IncrieTheme.DEFAULT)
}