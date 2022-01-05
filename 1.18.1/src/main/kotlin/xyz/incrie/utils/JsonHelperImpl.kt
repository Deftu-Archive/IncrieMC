package xyz.incrie.utils

import com.google.gson.JsonParser
import xyz.incrie.api.utils.JsonHelper

class JsonHelperImpl : JsonHelper {
    override fun parse(raw: String) = JsonParser.parseString(raw)
}