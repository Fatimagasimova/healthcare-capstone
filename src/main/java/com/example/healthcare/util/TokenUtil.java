package com.example.healthcare.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {

    private final String SECRET_KEY = "mysecretkey";  // bu hissəni təhlükəsizlik üçün dəyişməlisən
    private final long EXPIRATION_TIME = 86400000; // 1 gün (millisaniyə ilə)

    // Token yarat
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Token-dən istifadəçini al
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Token-dən rolu al
    public String extractUserRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // Token etibarlıdırmı?
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Token-dən claims al
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
