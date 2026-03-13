package com.example.app.util;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtil {

    private TokenUtil() {
    }

    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}