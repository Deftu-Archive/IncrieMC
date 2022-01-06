package xyz.incrie.launcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import xyz.incrie.launcher.dummy.JsonParser;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class LoadHandler {

    private static LoadHandler INSTANCE;

    public Class<?> start(Launcher launcher) {
        JsonObject versions = retrieveVersions(launcher);
        if (versions == null) throw new IllegalStateException("Could not read local versions JSON.");
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {
                    Paths.get(EnvironmentHandler.retrieveJarFilePath(launcher.getMajorVersion(), launcher.getGameVersion(), JsonHandler.get(versions, "latest", "version", "current").getAsString())).toUri().toURL()
            }, Launcher.class.getClassLoader());
            return Class.forName(
                    EnvironmentHandler.getLauncherClassName(),
                    true,
                    classLoader
            );
        } catch (Exception e) {
            throw new RuntimeException("An error occurred loading the Incrie loader file.", e);
        }
    }

    private JsonObject retrieveVersions(Launcher launcher) {
        try {
            JsonElement element = launcher.getJsonParser().parse(FileUtils.readFileToString(new File(EnvironmentHandler.retrieveJsonFilePath(launcher.getMajorVersion(), launcher.getGameVersion())), StandardCharsets.UTF_8));
            if (!element.isJsonObject()) throw new RuntimeException("The versions file is not a JSON object, this is not supported.");
            return element.getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred parsing the versions JSON file.", e);
        }
    }

    public static LoadHandler getInstance() {
        return INSTANCE == null ? INSTANCE = new LoadHandler() : INSTANCE;
    }

}