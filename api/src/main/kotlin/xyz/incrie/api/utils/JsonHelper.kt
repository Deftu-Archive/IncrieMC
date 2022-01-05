package xyz.incrie.api.utils

import com.google.gson.JsonElement

interface JsonHelper {
    fun parse(raw: String): JsonElement
}