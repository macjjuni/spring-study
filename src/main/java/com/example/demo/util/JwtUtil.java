package com.example.demo.util;

import com.example.demo.dto.MemberDto;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static final long TOKEN_VALIDITY = 86400000L;
    private static final Key SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    public static String generateToken(MemberDto member) {
        Date issuedAt = new Date();
        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_VALIDITY);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getName());
        claims.put("role", "User");
        claims.put("ext", expirationDate);
        claims.put("iat", issuedAt);

        return Jwts.builder()
                .setSubject(member.getName())
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .addClaims(claims)
                .compact();
    }

    public static String validateToken(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build();

        return jwtParser.parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String getExpireDate(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build();

        return jwtParser.parseClaimsJws(token)
                .getBody()
                .get("ext", Date.class)
                .toString();
    }
}
