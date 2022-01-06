package xyz.incrie.launcher.entrypoint;

public class StartupHandler {

    private static StartupHandler INSTANCE;

    public void start(Runnable callback) {
        callback.run();
    }

    public static StartupHandler getInstance() {
        return INSTANCE == null ? INSTANCE = new StartupHandler() : INSTANCE;
    }

}