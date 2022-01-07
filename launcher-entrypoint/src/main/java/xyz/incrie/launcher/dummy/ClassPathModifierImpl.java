package xyz.incrie.launcher.dummy;

import net.fabricmc.loader.impl.launch.FabricLauncherBase;

import java.net.URL;
import java.nio.file.Path;

public class ClassPathModifierImpl implements ClassPathModifier {

    public void add(Path path) {
        FabricLauncherBase.getLauncher().addToClassPath(path);
    }

    public void add(URL url) {
        throw new UnsupportedOperationException();
    }

}