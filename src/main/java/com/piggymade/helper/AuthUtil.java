package com.piggymade.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthUtil {


    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }

    public static String getSalt(String input) {
        String salt =  "'n3tT@k0oO]";
        return salt+input;
    }
}
