package com.example.poseexam.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {
    private SecretKey secretKey;
    public JwtUtil(@Value("${jwt.secret.key}") String secretKey) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    public String createToken(String category, String username, String role, Long expiration) {
        return Jwts.builder()
                .claim("category",category)// 카
                .claim("username",username)// 유
                .claim("role",role)// 롤
                .issuedAt(new Date(System.currentTimeMillis()))// 발날
                .expiration(new Date(System.currentTimeMillis()+expiration))// (발날+만료)2가지
                .signWith(this.secretKey)//시크릿키
                .compact();//컴
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build().parseSignedClaims(token)
                .getPayload().get("username").toString();
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build().parseSignedClaims(token)
                .getPayload().get("role").toString();
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build().parseSignedClaims(token)
                .getPayload().get("category").toString();
    }

    public boolean isExpired(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build().
                parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }




}
