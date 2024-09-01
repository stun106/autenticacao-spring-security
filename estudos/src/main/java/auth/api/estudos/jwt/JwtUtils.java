package auth.api.estudos.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class JwtUtils {
    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRETE_KEY = "1234567890-1234567890-123456789012";
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 1;

    private JwtUtils(){}

    private static Key gerenerateKey() {
        return Keys.hmacShaKeyFor(SECRETE_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String username, String role) {
        Date dataLimit = new Date();
        Date limit = toExpireDate(dataLimit);

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(dataLimit)
                .setExpiration(limit)
                .signWith(gerenerateKey(), SignatureAlgorithm.HS256)
                .claim("role", role)
                .compact();
        return new JwtToken(token);
    }

    public static String getUserEmailFromToken (String token) {
           return Objects.requireNonNull(getClaimsFromToken(token)).getSubject();
    }

    public static Boolean isTokenValid (String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(gerenerateKey()).build()
                    .parseClaimsJws(refactorToken(token)).getBody();
            return true;
        }catch (JwtException e) {
            log.error(String.format("Token inválido %s", e.getMessage()));
        }
        return false;
    }

    private static Claims getClaimsFromToken(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(gerenerateKey()).build()
                    .parseClaimsJws(refactorToken(token)).getBody();
        }catch (JwtException e) {
            log.error(String.format("Token inválido %s", e.getMessage()));
        }
        return null;
    }

    private static String refactorToken (String token) {
        if (token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
}
