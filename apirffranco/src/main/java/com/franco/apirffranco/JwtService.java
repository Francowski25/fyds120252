package com.franco.apirffranco;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.franco.apirffranco.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public String generateAccessToken(User user) {
        return buildToken(new HashMap<>(), user, JwtProperties.timeAuthMs);
    }

    public String generateRefreshToken(User user) {
        return buildToken(new HashMap<>(), user, JwtProperties.timeRefreshMs);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            User user,
            long expirationTimeMs) {

        extraClaims.put("role", user.getRole());
        extraClaims.put("idUser", user.getIdUser());

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail()) // identidad única
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, User user) {
        final String email = extractUsername(token);
        return email.equals(user.getEmail()) && !isTokenExpired(token);
    }

    public boolean isTokenValidSignature(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtProperties.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
