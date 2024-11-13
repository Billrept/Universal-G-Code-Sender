package com.willwinder.plugintest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class LastFilePathHelper {
    private static final String PROPERTIES_FILE = System.getProperty("user.home") + File.separator + "pluginSettings.properties";
    private static final String LAST_FILE_PATH_KEY = "lastFilePath";

    public static void saveLastFilePath(File file) {
        Properties properties = new Properties();
        properties.setProperty(LAST_FILE_PATH_KEY, file.getAbsolutePath());
        try (FileOutputStream out = new FileOutputStream(PROPERTIES_FILE)) {
            properties.store(out, "Plugin Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File loadLastFilePath() {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(in);
            String lastPath = properties.getProperty(LAST_FILE_PATH_KEY);
            if (lastPath != null) {
                return new File(lastPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
