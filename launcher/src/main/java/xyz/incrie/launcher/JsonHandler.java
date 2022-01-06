package xyz.incrie.launcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonHandler {

    public static boolean has(JsonObject object, String... checks) {
        for (String check : checks) {
            if (object.has(check)) {
                return true;
            }
        }

        return false;
    }

    public static JsonElement get(JsonObject object, String... checks) {
        JsonElement gotten = null;
        for (String check : checks) {
            JsonElement get = object.get(check);
            if (get != null) {
                gotten = get;
                break;
            }
        }

        return gotten;
    }

}