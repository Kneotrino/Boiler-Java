package com.piggymade.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for ConfigurationManager with real environment and properties
 */

@Slf4j
class ConfigurationManagerIntegrationTest {

    private ConfigurationManager configManager;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        configManager = new ConfigurationManager();
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Integration test with real properties file")
    void testRealPropertiesFileIntegration() {
        // This test will work if application.properties exists in classpath
        // Should load from properties file if available, otherwise use defaults

        String serverPort = configManager.getProperty("app.version");
        assertNotNull(serverPort, "app.version should not be null");

        String appName = configManager.getProperty("app.name");
        assertNotNull(appName, "App name should not be null");

        // Test integer parsing
        int portInt = configManager.getIntProperty("server.port", 8080);
        assertTrue(portInt > 0 && portInt <= 65535, "Port should be valid range");

        // Test boolean parsing
        boolean cacheEnabled = configManager.getBooleanProperty("cache.enabled", true);
        // Should not throw exception

        for (String s : Arrays.asList("Integration test completed successfully", "Server port: " + serverPort, "App name: " + appName, "Cache enabled: " + cacheEnabled)) {
            log.info(s);
        }
    }

    @Test
    @DisplayName("Test environment variable override in real scenario")
    void testEnvironmentVariableOverride() {
        // This test checks if actual environment variables override other sources
        // Note: This will only pass if you have actual environment variables set

        // Check if any environment variables are actually overriding
        String[] testKeys = {"SERVER_PORT", "DATABASE_URL", "APP_NAME"};

        for (String envKey : testKeys) {
            String envValue = System.getenv(envKey);
            if (envValue != null) {
                String propKey = envKey.toLowerCase().replace("_", ".");
                String configValue = configManager.getProperty(propKey);

                assertEquals(envValue.trim(), configValue,
                        "Environment variable " + envKey + " should override other sources");

                assertEquals("Environment Variable (" + envKey + ")",
                        configManager.getPropertySource(propKey),
                        "Property source should indicate environment variable");

                log.info("Found env override: " + envKey + "=" + envValue);
            }
        }
    }

    @Test
    @DisplayName("Test configuration printing produces output")
    void testConfigurationPrintingOutput() {
        configManager.printConfiguration();

        String output = outputStreamCaptor.toString();
        assertFalse(output.isEmpty(), "Configuration printing should produce output");
        assertTrue(output.contains("Configuration Summary"), "Output should contain header");
        assertTrue(output.contains("app.version"), "Output should contain server.port");

        System.setOut(standardOut); // Reset for this assertion
        log.info("Configuration output captured successfully");
    }

    @Test
    @DisplayName("Test all data types with real configuration")
    void testAllDataTypesIntegration() {
        // Test string properties
        String dbUrl = configManager.getProperty("database.url", "default-db-url");
        assertNotNull(dbUrl);

        // Test integer properties
        int maxConnections = configManager.getIntProperty("max.connections", 50);
        assertTrue(maxConnections > 0);

        // Test boolean properties
        boolean debugMode = configManager.getBooleanProperty("app.debug", false);
        // Should not throw exception

        // Test with fallbacks
        String nonExistent = configManager.getProperty("non.existent.key", "fallback-value");
        assertEquals("fallback-value", nonExistent);

        int nonExistentInt = configManager.getIntProperty("non.existent.int", 999);
        assertEquals(999, nonExistentInt);

        boolean nonExistentBool = configManager.getBooleanProperty("non.existent.bool", true);
        assertTrue(nonExistentBool);

        for (String s : Arrays.asList("All data type tests passed", "DB URL: " + dbUrl, "Max connections: " + maxConnections, "Debug mode: " + debugMode)) {
            log.info(s);
        }
    }

}