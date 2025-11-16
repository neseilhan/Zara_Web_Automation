package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties bulunamadı! Dosya src/test/resources altında olmalı.");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("config.properties yüklenemedi!", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("config.properties içinde '" + key + "' bulunamadı!");
        }
        return value.trim();
    }

    // Helper metodlar

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static String getEmail() {
        return get("zara.email");
    }

    public static String getPassword() {
        return get("zara.password");
    }
}
