package cinema.security.jwt;

import cinema.exception.InvalidJwtAuthentication;
import cinema.model.dto.response.JwtResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:security.properties")
public class JwtProvider {
    @Value("${jwt.token.validityTime}")
    private long validityTime;
    private Key secretKey;
    private final UserDetailsService userDetailsService;

    public JwtProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    private void init() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateJwt(String username, List<String> roles) {
        Date currentDate = new Date();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(currentDate.getTime() + validityTime))
                .setIssuedAt(currentDate)
                .signWith(secretKey)
                .compact();
    }

    public String parseJwt(HttpServletRequest httpRequest) {
        String jwtData = httpRequest.getHeader("Authorization");
        if (jwtData != null && jwtData.contains("Bearer ")) {
            return jwtData.substring(7);
        }
        return null;
    }

    public boolean validateJwt(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidJwtAuthentication("Invalid or expired JWT", ex);
        }
    }

    public String getUsername(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    public JwtResponseDto generateResponseEntity(String jwt) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        LocalDateTime issuedAt =
                LocalDateTime.ofInstant(body.getIssuedAt().toInstant(), ZoneId.systemDefault());
        LocalDateTime expiration =
                LocalDateTime.ofInstant(body.getExpiration().toInstant(), ZoneId.systemDefault());
        return new JwtResponseDto(body.getSubject(), issuedAt, expiration, jwt);
    }

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }
}
