package xyz.incrie.launcher.dummy;

import com.google.gson.JsonElement;

public interface JsonParser {
    JsonElement parse(String raw);
}