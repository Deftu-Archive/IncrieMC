package xyz.incrie.launcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class LoadHandler {

    private static LoadHandler INSTANCE;

    public void start(Launcher launcher) {
        JsonObject versions = retrieveVersions(launcher);
        if (versions == null) throw new IllegalStateException("Could not read local versions JSON.");
        launcher.getClassPathModifier().add(Paths.get(EnvironmentHandler.retrieveJarFilePath(launcher.getMajorVersion(), launcher.getGameVersion(), JsonHandler.get(versions, "latest", "version", "current").getAsString())));
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