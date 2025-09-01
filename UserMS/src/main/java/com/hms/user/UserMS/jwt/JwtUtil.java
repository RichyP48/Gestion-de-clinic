package com.hms.user.UserMS.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final Long JWT_TOKEN_VALIDITY=5*60*60L;
    private static  final String SECRET="b9f867ca6f7f9b081f154de73bff0791ca3dc4841c77e8d09399cc268b9c47f1e12cb658c380733b0b805545920cc4ffed2418c60ec4df9409ed0536eb333e7e";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        CustomUserDetails user=(CustomUserDetails)  userDetails;
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("name", user.getName());
        claims.put("profileId", user.getProfileId());
        return doGenerateToken(claims, user.getUsername());

    }
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*100))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
    }
}
