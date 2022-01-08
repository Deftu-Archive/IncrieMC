package xyz.incrie.api.text.impl

import xyz.incrie.api.text.Text

class MutableText(
    private val content: String
) : Text {
    override fun asString() = content
    override fun copy() = MutableText(content)
}