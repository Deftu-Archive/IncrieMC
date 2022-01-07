package xyz.incrie.launcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class DownloadHandler {

    private static DownloadHandler INSTANCE;

    public void start(Launcher launcher) {
        if (!EnvironmentHandler.isLocal()) {
            JsonObject versions = requestVersions(launcher);
            if (versions != null) {
                if (!JsonHandler.has(versions, "latest", "version", "current")) throw new IllegalStateException("The JSON found when getting the version download does not contain any of the valid version names.");
                JsonElement versionRaw = JsonHandler.get(versions, "latest", "version", "current");
                if (versionRaw == null) throw new IllegalStateException("The version found in the versions JSON was somehow null.");
                if (!versionRaw.isJsonPrimitive() || !versionRaw.getAsJsonPrimitive().isString()) throw new IllegalStateException("The version found in the versions JSON was the wrong type.");
                String version = versionRaw.getAsString();

                AtomicBoolean finished = new AtomicBoolean(false);
                HttpHandler.request(new Request.Builder()
                        .url(EnvironmentHandler.retrieveDownloadJarUrl(launcher.getMajorVersion(), launcher.getGameVersion(), version))
                        .get()
                        .build(), response -> {
                    ResponseBody body = response.body();
                    if (body == null) throw new IllegalStateException("The response body returned from the JAR download URL was null.");

                    FileInputStream fileStream = null;
                    try {
                        fileStream = new FileInputStream(EnvironmentHandler.retrieveJarFilePath(launcher.getMajorVersion(), launcher.getGameVersion(), version));
                    } catch (Exception ignored) {
                    }

                    InputStream stream = body.byteStream();
                    if (!FileHandler.compare(stream, fileStream)) {
                        AtomicBoolean downloadFinished = new AtomicBoolean(false);
                        new Thread(() -> {
                            try {
                                BufferedInputStream readStream = new BufferedInputStream(stream);
                                File file = new File(EnvironmentHandler.retrieveJarFilePath(launcher.getMajorVersion(), launcher.getGameVersion(), version));
                                FileOutputStream output = FileUtils.openOutputStream(file);

                                byte[] buffer = new byte[8192];
                                int n;
                                while (-1 != (n = readStream.read(buffer))) {
                                    output.write(buffer, 0, n);
                                }

                                output.close();

                                downloadFinished.set(true);
                            } catch (Exception e) {
                                throw new RuntimeException("An error occurred downloading the Incrie JAR file.", e);
                            }
                        }).start();

                        try {
                            while (!downloadFinished.get()) Thread.sleep(1000);
                        } catch (Exception e) {
                            throw new RuntimeException("An error occurred while waiting for the Incrie launcher download to complete.", e);
                        }
                    }

                    finished.set(true);
                });

                try {
                    while (!finished.get()) Thread.sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException("An error occurred while waiting for the Incrie launcher downloader.", e);
                }
            }
        }
    }

    private JsonObject requestVersions(Launcher launcher) {
        AtomicReference<JsonObject> value = new AtomicReference<>();
        HttpHandler.requestJson(new Request.Builder()
                .url(EnvironmentHandler.getDownloadJsonUrl())
                .get()
                .build(), element -> {
            if (element.isJsonObject()) {
                value.set(element.getAsJsonObject());
                try {
                    FileUtils.writeStringToFile(new File(EnvironmentHandler.retrieveJsonFilePath(launcher.getMajorVersion(), launcher.getGameVersion())), element.toString(), StandardCharsets.UTF_8);
                } catch (Exception e) {
                    throw new RuntimeException("An error occurred writing the Incrie versions file.", e);
                }
            } else {
                throw new IllegalArgumentException("The JSON found when getting the version download was not an object, this is not supported!");
            }
        });

        return value.get();
    }

    public static DownloadHandler getInstance() {
        return INSTANCE == null ? INSTANCE = new DownloadHandler() : INSTANCE;
    }

}