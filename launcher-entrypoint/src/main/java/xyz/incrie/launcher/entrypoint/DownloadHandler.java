package xyz.incrie.launcher.entrypoint;

import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadHandler {

    private static DownloadHandler INSTANCE;

    public void start() {
        IncrieSetup.LOGGER.info("Starting download checks for Incrie launcher...");
        AtomicBoolean finished = new AtomicBoolean(false);
        HttpHandler.request(new Request.Builder()
                .url(EnvironmentHandler.getDownloadUrl())
                .get()
                .build(), response -> {
            ResponseBody body = response.body();
            if (body == null) throw new IllegalStateException("There was no body with the response for the loader download request.");

            FileInputStream fileStream = null;
            try {
                fileStream = new FileInputStream(EnvironmentHandler.getLauncherPath());
            } catch (Exception ignored) {
            }

            InputStream stream = body.byteStream();
            if (!FileHandler.compare(new BufferedInputStream(stream), fileStream)) {
                if (fileStream != null) {
                    try {
                        fileStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                long startTime = System.currentTimeMillis();
                IncrieSetup.LOGGER.info("Starting download for Incrie launcher... ({})", EnvironmentHandler.getDownloadUrl());

                AtomicBoolean downloadFinished = new AtomicBoolean(false);

                new Thread(() -> {
                    try {
                        BufferedInputStream readStream = new BufferedInputStream(stream);
                        File file = new File(EnvironmentHandler.getLauncherPath());
                        FileOutputStream output = FileUtils.openOutputStream(file);

                        byte[] buffer = new byte[8192];
                        int n;
                        while (-1 != (n = readStream.read(buffer))) {
                            output.write(buffer, 0, n);
                        }

                        output.close();

                        downloadFinished.set(true);
                    } catch (Exception e) {
                        throw new RuntimeException("An error occurred downloading the Incrie launcher file.", e);
                    }
                }).start();

                try {
                    while (!downloadFinished.get()) Thread.sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException("An error occurred while waiting for the Incrie launcher download to complete.", e);
                }

                IncrieSetup.LOGGER.info("Download for Incrie launcher finished in {}ms.", System.currentTimeMillis() - startTime);
            }

            finished.set(true);
        });

        try {
            while (!finished.get()) Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while waiting for the Incrie launcher downloader.", e);
        }
    }

    public static DownloadHandler getInstance() {
        return INSTANCE == null ? INSTANCE = new DownloadHandler() : INSTANCE;
    }

}