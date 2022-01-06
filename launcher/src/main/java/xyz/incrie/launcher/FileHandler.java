package xyz.incrie.launcher;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.security.MessageDigest;

public class FileHandler {

    public static boolean compare(InputStream stream1, InputStream stream2) {
        if (stream1 == null || stream2 == null) return false;
        return hash(stream1).compareTo(hash(stream2)) == 0;
    }

    public static String hash(InputStream stream) {
        try {
            byte[] bytes = IOUtils.toByteArray(stream);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] sha256 = digest.digest(bytes);
            StringBuilder converter = new StringBuilder();
            for (byte aByte : sha256) converter.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            return converter.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash a stream.", e);
        }
    }

}