package com.example.mediaScocial.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class jwtConstant {
    // Generate a secure random key for JWT secret
    public static SecretKey JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Encode the generated key as a Base64-encoded string
    public static String JWT_SECRET = Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getEncoded());

    // Define the JWT header
    public static String JWT_HEADER = "Authorization";
}
