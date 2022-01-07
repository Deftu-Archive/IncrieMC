package xyz.incrie.launcher.entrypoint;

import com.google.gson.JsonObject;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import xyz.incrie.launcher.dummy.ClassPathModifier;
import xyz.incrie.launcher.dummy.ClassPathModifierImpl;
import xyz.incrie.launcher.dummy.JsonParser;
import xyz.incrie.launcher.dummy.JsonParserImpl;

import java.nio.file.Paths;

public class LoadHandler {

    private static LoadHandler INSTANCE;

    public Runnable start() {
        try {
            ClassPathModifier classPathModifier = new ClassPathModifierImpl();
            classPathModifier.add(Paths.get(EnvironmentHandler.getLauncherPath()));
            return (Runnable) Class.forName(EnvironmentHandler.getLauncherClassName()).getConstructor(JsonObject.class, JsonParser.class, ClassPathModifier.class).newInstance(createDataJson(), new JsonParserImpl(), classPathModifier);
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