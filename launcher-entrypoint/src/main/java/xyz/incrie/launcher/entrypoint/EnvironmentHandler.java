package xyz.incrie.launcher.entrypoint;

public class EnvironmentHandler {

    public static String getDownloadUrl() {
        return System.getProperty("incrie.entry.download", "https://raw.githubusercontent.com/Incrie/DataStorage/main/downloads/v1/IncrieLauncher.jar");
    }

    public static String getMajorVersion() {
        return System.getProperty("incrie.entry.version", "v1");
    }

    public static String getLauncherPath() {
        return String.format("Incrie/%s/%s",
                getMajorVersion(),
                "IncrieLauncher.jar");
    }

    public static String getLauncherClassName() {
        return System.getProperty("incrie.entry.launcher", "xyz.incrie.launcher.Launcher");
    }

}