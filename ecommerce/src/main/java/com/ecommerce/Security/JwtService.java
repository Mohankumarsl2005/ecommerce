package com.ecommerce.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtService {

    private final String secretKey =
            "ecommerce-jwt-secret-key-2026-mohan-secure-auth-9f7K2xLm";

    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token){

        Claims claims = Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload();

        return claims.getSubject();

    }

    public Boolean isTokenValid(String token){
        try{
            Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token);

            return true;
        }catch(Exception e){
            return false;
        }
    }
}
