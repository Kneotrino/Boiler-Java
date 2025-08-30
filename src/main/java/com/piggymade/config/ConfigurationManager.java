package com.piggymade.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Configuration manager that reads properties with priority:
 * 1. Docker environment variables (highest priority)
 * 2. Properties file
 * 3. Default hardcoded values (lowest priority)
 */
@Slf4j
public class ConfigurationManager {

    private static final String DEFAULT_PROPERTIES_FILE = "piggy-made.properties";
    private final Properties properties;
    private final Map<String, String> defaultValues;

    public ConfigurationManager() {
        this(DEFAULT_PROPERTIES_FILE);
    }

    public ConfigurationManager(String propertiesFileName) {
        this.properties = new Properties();
        this.defaultValues = new HashMap<>();

        // Initialize default values
        initializeDefaults();

        // Load properties file
        loadPropertiesFile(propertiesFileName);
    }

    /**
     * Initialize default hardcoded values
     */
    private void initializeDefaults() {
        defaultValues.put("app.name", "MyApplication");
        defaultValues.put("app.version", "1.0.0");
        defaultValues.put("cache.enabled", "true");
    }

    /**
     * Load properties from file
     */
    private void loadPropertiesFile(String fileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
                log.info("Properties file '{}' loaded successfully", fileName);
            } else {
                log.info("Properties file '{}' not found, using defaults and environment variables only", fileName);
            }
        } catch (IOException e) {
            log.error("Error loading properties file: {}", fileName, e);
        }
    }

    /**
     * Get property value with priority: Environment > Properties File > Default
     */
    public String getProperty(String key) {
        // Priority 1: Check Docker environment variables
        String envValue = System.getenv(key.toUpperCase().replace(".", "_"));
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue.trim();
        }

        // Priority 2: Check properties file
        String propertyValue = properties.getProperty(key);
        if (propertyValue != null && !propertyValue.trim().isEmpty()) {
            return propertyValue.trim();
        }

        // Priority 3: Check default values
        String defaultValue = defaultValues.get(key);
        if (defaultValue != null) {
            return defaultValue;
        }

        return null;
    }

    /**
     * Get property value with a fallback default
     */
    public String getProperty(String key, String fallbackDefault) {
        String value = getProperty(key);
        return value != null ? value : fallbackDefault;
    }

    /**
     * Get property as integer
     */
    public int getIntProperty(String key) {
        String value = getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' not found");
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Property '" + key + "' is not a valid integer: " + value);
        }
    }

    /**
     * Get property as integer with default value
     */
    public int getIntProperty(String key, int defaultValue) {
        try {
            return getIntProperty(key);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    /**
     * Get property as boolean
     */
    public boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' not found");
        }
        return Boolean.parseBoolean(value);
    }

    /**
     * Get property as boolean with default value
     */
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        try {
            return getBooleanProperty(key);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    /**
     * Check if a property exists (in any of the sources)
     */
    public boolean hasProperty(String key) {
        return getProperty(key) != null;
    }

    /**
     * Get the source of a property value for debugging
     */
    public String getPropertySource(String key) {
        // Check environment variables
        String envKey = key.toUpperCase().replace(".", "_");
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return "Environment Variable (" + envKey + ")";
        }

        // Check properties file
        String propertyValue = properties.getProperty(key);
        if (propertyValue != null && !propertyValue.trim().isEmpty()) {
            return "Properties File";
        }

        // Check defaults
        if (defaultValues.containsKey(key)) {
            return "Default Value";
        }

        return "Not Found";
    }

    /**
     * Print all configuration values and their sources (useful for debugging)
     */
    public void printConfiguration() {
        log.info("=== Configuration Summary ===");

        // Collect all unique keys
        java.util.Set<String> allKeys = new java.util.HashSet<>();
        allKeys.addAll(defaultValues.keySet());
        allKeys.addAll(properties.stringPropertyNames());

        // Add environment variable keys (convert back from ENV_VAR format)
        System.getenv().keySet().stream()
                .filter(envKey -> envKey.contains("_"))
                .map(envKey -> envKey.toLowerCase().replace("_", "."))
                .forEach(allKeys::add);

        allKeys.stream().sorted().forEach(key -> {
            String value = getProperty(key);
            String source = getPropertySource(key);
            System.out.printf("%-25s = %-20s [%s]%n", key, value, source);
        });

        log.info("================================");
    }

}