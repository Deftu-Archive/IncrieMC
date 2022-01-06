package xyz.incrie.launcher;

import com.google.gson.JsonObject;
import xyz.incrie.launcher.dummy.JsonParser;

public class Launcher implements Runnable {

    private final String majorVersion;
    private final String gameVersion;
    private final JsonParser jsonParser;

    public Launcher(JsonObject data, JsonParser jsonParser) {
        if (!data.has("major_version")) throw new IllegalStateException("The data provided by the parent does not include the major version.");
        this.majorVersion = data.get("major_version").getAsString();
        if (!data.has("game_version")) throw new IllegalStateException("The data provided by the parent does not include the game version.");
        this.gameVersion = data.get("game_version").getAsString();

        this.jsonParser = jsonParser;
    }

    public void run() {
        DownloadHandler.getInstance().start(this);
        StartupHandler.getInstance().start(LoadHandler.getInstance().start(this));
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

}