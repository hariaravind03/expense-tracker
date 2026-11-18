package com.hari.Expense_Tracker.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {


    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secret){
        byte[] keyBytes = Base64.getDecoder()
                .decode(secret);

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

//    HMAC-SHA256(header + payload, secret)
    public String GenerateToken(String email){
        Long currentTime=System.currentTimeMillis();

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(currentTime))
                .expiration(
                        new Date(currentTime + 1000 * 30 * 60)
                )
                .signWith(secretKey)
                .compact();
    }



    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }
}
