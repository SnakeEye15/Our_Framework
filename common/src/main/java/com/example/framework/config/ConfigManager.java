package com.example.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key, int fallback) {
        try {
            return Integer.parseInt(props.getProperty(key));
        } catch (Exception e) {
            return fallback;
        }
    }

    public static boolean getBoolean(String key, boolean fallback) {
        try {
            return Boolean.parseBoolean(props.getProperty(key));
        } catch (Exception e) {
            return fallback;
        }
    }
}
