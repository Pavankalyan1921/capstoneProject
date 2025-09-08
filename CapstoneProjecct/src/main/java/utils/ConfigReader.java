package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties props;

    // Load properties only once
    private static void loadProperties() {
        if (props == null) {
            props = new Properties();
            try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
                props.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
    }

    // Get property by key
    public static String get(String key) {
        loadProperties();
        return props.getProperty(key);
    }

    // Optional: convenience method for boolean values
    public static boolean getBoolean(String key) {
        loadProperties();
        return Boolean.parseBoolean(props.getProperty(key));
    }

    // Optional: get integer value
    public static int getInt(String key) {
        loadProperties();
        return Integer.parseInt(props.getProperty(key));
    }
}
