package xyz.incrie.text

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.AbstractClientPlayerEntity
import xyz.incrie.api.text.ChatManager
import xyz.incrie.api.text.Text
import xyz.incrie.utils.*

class ChatManagerImpl : ChatManager {
    val senderHistory: MutableList<AbstractClientPlayerEntity> = mutableListOf()
    override fun send(text: Text) = MinecraftClient.getInstance().inGameHud.chatHud.addMessage(text.toMinecraftText())
    override fun remove(index: Int) { MinecraftClient.getInstance().inGameHud.chatHud.messageHistory.removeAt(index) }
    override fun getMessages() = MinecraftClient.getInstance().inGameHud.chatHud.messageHistory.map { Text.of(it) }
    override fun getSender(index: Int): AbstractClientPlayerEntity = senderHistory[index]
}