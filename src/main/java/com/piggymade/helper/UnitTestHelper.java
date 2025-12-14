package com.piggymade.helper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnitTestHelper {

    public static final String LOCAL= "local";

    public static boolean isLocalEnvironment() {
        try {
            return LOCAL.equalsIgnoreCase(System.getProperty("environment"));
        } catch (Exception e) {
            log.error("isLocalEnvironment = {}", e.getMessage());
            return false;
        }
    }
}
