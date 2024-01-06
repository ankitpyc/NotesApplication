package com.NotesApplication.database.dto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your-secret-key";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        String base64EncodedSecretKey = base64Encode(SECRET_KEY);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, base64EncodedSecretKey)
                .compact();
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private static Claims extractClaims(String token) {
        String base64EncodedSecretKey = base64Encode(SECRET_KEY);

        return Jwts.parser()
                .setSigningKey(base64EncodedSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateToken(String token) {
        try {
            String base64EncodedSecretKey = base64Encode(SECRET_KEY);
            Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        // Example usage
        String username = "john.doe";
        String token = generateToken(username);
        System.out.println("Generated Token: " + token);

        if (validateToken(token)) {
            String extractedUsername = extractUsername(token);
            System.out.println("Extracted Username: " + extractedUsername);
        } else {
            System.out.println("Invalid Token");
        }
    }
}