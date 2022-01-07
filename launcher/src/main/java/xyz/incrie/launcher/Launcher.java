package xyz.incrie.launcher;

import com.google.gson.JsonObject;
import xyz.incrie.launcher.dummy.ClassPathModifier;
import xyz.incrie.launcher.dummy.JsonParser;

public class Launcher implements Runnable {

    private final String majorVersion;
    private final String gameVersion;

    private final JsonParser jsonParser;
    private final ClassPathModifier classPathModifier;

    public Launcher(JsonObject data, JsonParser jsonParser, ClassPathModifier classPathModifier) {
        if (!data.has("major_version")) throw new IllegalStateException("The data provided by the parent does not include the major version.");
        this.majorVersion = data.get("major_version").getAsString();
        if (!data.has("game_version")) throw new IllegalStateException("The data provided by the parent does not include the game version.");
        this.gameVersion = data.get("game_version").getAsString();

        this.jsonParser = jsonParser;
        this.classPathModifier = classPathModifier;
    }

    public void run() {
        DownloadHandler.getInstance().start(this);
        LoadHandler.getInstance().start(this);
        StartupHandler.getInstance().start();
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public JsonParser getJsonParser() {
        return jsonParser;
    }

    public ClassPathModifier getClassPathModifier() {
        return classPathModifier;
    }

}