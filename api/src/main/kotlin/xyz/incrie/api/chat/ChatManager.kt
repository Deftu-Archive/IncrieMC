package xyz.incrie.api.chat

import xyz.incrie.api.text.Text

interface ChatManager {
    fun send(text: Text)
}