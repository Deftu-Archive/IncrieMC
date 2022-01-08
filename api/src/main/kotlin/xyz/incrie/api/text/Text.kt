package xyz.incrie.api.text

import xyz.incrie.api.text.impl.MutableText

interface Text {
    fun asString(): String
    fun copy(): Text
    companion object {
        @JvmStatic fun of(content: String) = MutableText(content)
    }
}