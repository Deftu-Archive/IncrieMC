package xyz.incrie.launcher.dummy;

import com.google.gson.JsonElement;

public class JsonParserImpl implements JsonParser {
    public JsonElement parse(String raw) {
        return com.google.gson.JsonParser.parseString(raw);
    }
}