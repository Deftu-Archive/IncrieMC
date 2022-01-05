package xyz.incrie.utils

import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

fun String.toText() = Text.of(this)
fun String.toTranslatedText() = TranslatableText(this)