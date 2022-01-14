package xyz.incrie.api.text

import net.minecraft.client.network.AbstractClientPlayerEntity

interface ChatManager {
    fun send(text: Text)
    fun remove(index: Int)
    fun getMessages(): List<Text>
    fun getMessage(index: Int) = getMessages()[index]
    fun getLatestMessage() = getMessage(0)
    fun getSender(index: Int): AbstractClientPlayerEntity
    fun getLatestSender(index: Int) = getSender(0)
}