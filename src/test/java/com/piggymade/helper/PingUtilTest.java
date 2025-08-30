package com.piggymade.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PingUtilTest {

    @Test
    void testValidConnection() {
        // Using a well-known server (Google). Could still fail if offline.
        boolean result = PingUtil.isConnectedToServer("http://www.google.com", 2000);
        assertTrue(result, "Should connect successfully to Google");
    }

    @Test
    void testInvalidConnection() {
        // Invalid address should fail
        boolean result = PingUtil.isConnectedToServer("http://invalid.nonexistent.server", 2000);
        assertFalse(result, "Should fail to connect to an invalid server");
    }

    @Test
    void testMalformedUrl() {
        boolean result = PingUtil.isConnectedToServer("not-a-valid-url", 2000);
        assertFalse(result, "Should fail due to malformed URL");
    }
}
