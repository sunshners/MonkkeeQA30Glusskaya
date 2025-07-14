package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class PropertyReader {
    private static final String DEFAULT_PROPERTIES_PATH = "/config.properties";
    private static volatile Properties properties;
    
    private PropertyReader() {
        throw new IllegalStateException("Utility class");
    }

    private static synchronized void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream inputStream = PropertyReader.class.getResourceAsStream(DEFAULT_PROPERTIES_PATH)) {
                if (inputStream != null) {
                    properties.load(inputStream);
                    log.info("Properties loaded successfully from {}", DEFAULT_PROPERTIES_PATH);

                    String additionalConfig = properties.getProperty("config_file");
                    if (additionalConfig != null) {
                        loadAdditionalProperties(additionalConfig);
                    }
                } else {
                    log.error("Properties file not found: {}", DEFAULT_PROPERTIES_PATH);
                }
            } catch (IOException e) {
                log.error("Error loading properties file", e);
                throw new RuntimeException("Failed to load properties file", e);
            }
        }
    }

    private static void loadAdditionalProperties(String path) {
        try (InputStream inputStream = PropertyReader.class.getResourceAsStream(normalizePath(path))) {
            Properties additionalProps = new Properties();
            if (inputStream != null) {
                additionalProps.load(inputStream);
                properties.putAll(additionalProps);
                log.info("Additional properties loaded from {}", path);
            } else {
                log.warn("Additional properties file not found: {}", path);
            }
        } catch (IOException e) {
            log.error("Error loading additional properties file", e);
        }
    }

    private static String normalizePath(String path) {
        return path.charAt(0) != '/' ? "/" + path : path;
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        String value = properties.getProperty(key);
        if (value == null) {
            log.warn("Property '{}' not found", key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }
}