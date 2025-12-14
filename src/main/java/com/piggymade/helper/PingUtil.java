package com.piggymade.helper;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class PingUtil {
    public static boolean isConnectedToServer(String url, int timeout) {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            log.error("Error ping url "+url,e);

            // todo Handle exceptions
            return false;
        }
    }


    // ✅ Overloaded method with default timeout
    public static boolean isConnectedToServer(String url) {
        // choose a sensible default (e.g., 2000 ms)
        return isConnectedToServer(url, 30_000);
    }
}
