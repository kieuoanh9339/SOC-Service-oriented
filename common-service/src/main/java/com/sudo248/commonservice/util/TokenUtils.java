package com.sudo248.commonservice.util;

import com.sudo248.domain.common.Constants;
import com.sudo248.domain.common.ErrorMessage;
import com.sudo248.domain.exception.ApiException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TokenUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-token}")
    private long expirationToken;

    @Value("${jwt.expiration-refresh-token}")
    private long expirationRefreshToken;

    public String generateToken(String userId) {
        var now = new Date();
        var expireDate = new Date(now.getTime() + expirationToken);
        return Jwts.builder()
                .setId(userId)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String token) {
        var now = new Date();
        var expireDate = new Date(now.getTime() + expirationRefreshToken);
        return Jwts.builder()
                .setSubject(token)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        var claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getId();
    }

    public boolean validateToken(String token) throws ApiException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorMessage.TOKEN_INVALID);
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorMessage.TOKEN_EXPIRE);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorMessage.TOKEN_UNSUPPORTED);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw new ApiException(HttpStatus.UNAUTHORIZED, "JWT claims string is empty.");
        }
    }
}
