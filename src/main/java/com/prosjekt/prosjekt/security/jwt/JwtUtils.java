package com.prosjekt.prosjekt.security.jwt;

import com.prosjekt.prosjekt.appuser.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A lot of code derived from https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java
 */
@Service
public class JwtUtils {
    /**
     * JWT Secret key
     */
    @Value("${prosjekt.app.jwtSecret}")
    private String jwtSecret;

    /**
     * Expiration date of token.
     */
    @Value("${prosjekt.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(AppUser appUser) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, appUser.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject) //Subject == User to be subjugated
                .setIssuedAt(new Date()) //Time of generation
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) //Expiration date
                .signWith(SignatureAlgorithm.HS512, jwtSecret) //Signature
                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, AppUser appUser) {
        final String email = extractEmail(token);
        return (email.equals(appUser.getEmail()) && !isTokenExpired(token));
    }
}

