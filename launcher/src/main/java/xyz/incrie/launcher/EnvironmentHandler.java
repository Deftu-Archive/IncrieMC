package xyz.incrie.launcher;

public class EnvironmentHandler {

    public static boolean isLocal() {
        return Boolean.parseBoolean(System.getProperty("incrie.launcher.local", "false"));
    }

    public static String getDownloadJsonUrl() {
        return System.getProperty("incrie.launcher.download.json", "https://raw.githubusercontent.com/Incrie/DataStorage/main/downloads/version.json");
    }

    public static String retrieveJsonFilePath(String majorVersion, String gameVersion) {
        return String.format("Incrie/%s/%s/version.json",
                majorVersion,
                gameVersion);
    }

    public static String retrieveDownloadJarUrl(String majorVersion, String gameVersion, String version) {
        return System.getProperty("incrie.launcher.download.jar", "https://raw.githubusercontent.com/Incrie/DataStorage/main/downloads/{major}/{game}/{version}")
                .replace("{major}", majorVersion)
                .replace("{game}", gameVersion)
                .replace("{version}", version);
    }

    public static String retrieveJarFilePath(String majorVersion, String gameVersion, String version) {
        return String.format("Incrie/%s/%s/%s",
                majorVersion,
                gameVersion,
                "Incrie-" + version + ".jar");
    }

    public static String getLauncherClassName() {
        return System.getProperty("incrie.launcher.class", "xyz.incrie.api.IncrieLauncher");
    }

}