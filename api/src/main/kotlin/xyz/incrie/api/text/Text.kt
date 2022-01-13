package xyz.incrie.api.text

import xyz.incrie.api.text.impl.MutableText

interface Text {
    fun asString(): String
    fun copy(): Text
    fun withReplaced(vararg replacements: String): Text {
        var value: String = asString()
        for (replacement in replacements) value = value.replaceFirst("{}", replacement)
        return of(value)
    }

    companion object {
        @JvmStatic fun of(content: String) = MutableText(content)
    }
}