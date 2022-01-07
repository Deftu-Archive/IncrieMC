package xyz.incrie.launcher.dummy;

import java.net.URL;
import java.nio.file.Path;

public interface ClassPathModifier {
    void add(Path path);
    void add(URL url);
}