package com.example.bostadsplattform.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ------------------- CONFIG -------------------
    private final Key key;                        // HS512 safe key
    private final long expiration = 1000 * 60 * 60 * 24; // 24 hours

    public JwtUtil() {
        // generates a random strong key suitable for HS512
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    // ------------------- TOKEN GENERATION -------------------
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)          // store role inside JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // ------------------- TOKEN VALIDATION -------------------
    public boolean validateToken(String token, String email) {
        String tokenEmail = getEmailFromToken(token);
        return (tokenEmail.equals(email)) && !isTokenExpired(token);
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
