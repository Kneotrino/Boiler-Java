package com.piggymade.helper;

import java.util.Map;

public class MapUtil {

    public static String getStringParam(Map<String, Object> defaultParameter, String key, String defaultValue) {

        try {
            return defaultParameter.getOrDefault(key, defaultValue).toString();
        } catch (Exception e) {
            return defaultValue;
        }

    }

    public static int getIntParam(Map<String, Object> defaultParameter, String key, int defaultValue) {
        try {
            return Integer.parseInt(defaultParameter.getOrDefault(key, String.valueOf(defaultValue)).toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public static double getDoubleParam(Map<String, Object> defaultParameter, String key, double defaultValue) {
        try {
            return Double.parseDouble(
                    defaultParameter.getOrDefault(key, String.valueOf(defaultValue)).toString()
            );
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static long getLongParam(Map<String, Object> defaultParameter, String key, long defaultValue) {
        try {
            return Long.parseLong(
                    defaultParameter.getOrDefault(key, String.valueOf(defaultValue)).toString()
            );
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
