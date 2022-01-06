package xyz.incrie.launcher.entrypoint;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IncrieSetup implements PreLaunchEntrypoint {

    public static final Logger LOGGER = LogManager.getLogger("Incrie Setup");

    public void onPreLaunch() {
        DownloadHandler.getInstance().start();
        StartupHandler.getInstance().start(LoadHandler.getInstance().start());
    }

}