package xyz.incrie.launcher.entrypoint;

import com.google.gson.JsonObject;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import xyz.incrie.launcher.dummy.JsonParser;
import xyz.incrie.launcher.dummy.JsonParserImpl;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadHandler {

    private static LoadHandler INSTANCE;

    public Runnable start() {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {
                    new File(EnvironmentHandler.getLauncherPath()).toURI().toURL()
            }, IncrieSetup.class.getClassLoader());
            return (Runnable) Class.forName(
                    EnvironmentHandler.getLauncherClassName(),
                    true,
                    classLoader
            ).getConstructor(JsonObject.class, JsonParser.class).newInstance(createDataJson(), new JsonParserImpl());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred loading the Incrie launcher file.", e);
        }
    }

    private JsonObject createDataJson() {
        JsonObject value = new JsonObject();
        value.addProperty("major_version", EnvironmentHandler.getMajorVersion());
        value.addProperty("game_version", FabricLoaderImpl.INSTANCE.getGameProvider().getNormalizedGameVersion());
        return value;
    }

    public static LoadHandler getInstance() {
        return INSTANCE == null ? INSTANCE = new LoadHandler() : INSTANCE;
    }

}