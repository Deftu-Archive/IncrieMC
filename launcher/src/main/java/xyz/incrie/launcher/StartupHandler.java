package xyz.incrie.launcher;

import java.lang.reflect.Method;

public class StartupHandler {

    private static StartupHandler INSTANCE;

    public void start(Class<?> clazz) {
        try {
            Method method = clazz.getDeclaredMethod("initialize");
            method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred starting Incrie.", e);
        }
    }

    public static StartupHandler getInstance() {
        return INSTANCE == null ? INSTANCE = new StartupHandler() : INSTANCE;
    }

}