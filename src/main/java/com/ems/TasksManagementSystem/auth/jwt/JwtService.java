package com.ems.TasksManagementSystem.auth.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private Logger logger= LoggerFactory.getLogger(JwtService.class);
    private final String SECRET_KEY="TXlWZXJ5U2VjcmV0Snd0S2V5VGhhdElzU2FmZUFuZFNlY3VyZTEyMzQ1Ng==";

    public String generateToken(UserDetails userDetails){
        String authority = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        logger.info("Claim = " + authority);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles",authority)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*10))
                .signWith(getSignKey())
                .compact();

    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String userName=extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractCalims(token,Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
        return extractCalims(token,Claims::getSubject);
    }

    private <T>T extractCalims(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes= Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
