package xyz.incrie.chat

import net.minecraft.client.MinecraftClient
import xyz.incrie.api.chat.ChatManager
import xyz.incrie.api.text.Text
import xyz.incrie.utils.*

class ChatManagerImpl : ChatManager {
    override fun send(text: Text) = MinecraftClient.getInstance().inGameHud.chatHud.addMessage(text.toMinecraftText())

}