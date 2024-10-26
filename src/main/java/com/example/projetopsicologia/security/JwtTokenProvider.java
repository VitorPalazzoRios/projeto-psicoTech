package com.example.projetopsicologia.security;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenProvider {
    private String secretKey = "your-secret-key";
    private long validityInMilliseconds = 3600000; // 1 hora



    
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setExpiration(Date.from(Instant.now().plus(validityInMilliseconds, ChronoUnit.MILLIS)))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            Date expirationDate = claims.getBody().getExpiration();
            Date now = Date.from(Instant.now());
            return !expirationDate.before(now);
        } catch (Exception e) {
            return false;
        }
    }
    

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}

