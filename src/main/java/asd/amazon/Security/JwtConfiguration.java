package asd.amazon.Security;

import asd.amazon.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.*;

@Configuration
public class JwtConfiguration {

    @Value("${jwt.secret}")
    private String secret;

    private Key secretKey;

    @PostConstruct
    public void init(){
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String extractUserName(String token) {
        return getClaimsFromToken(token)
                .getSubject();
    }

    public boolean validateToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration()
                .after(new Date());
    }

    private Claims getClaimsFromToken(String token){

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(Account user){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id",user.getId());
        claims.put("username",user.getUsername());


        Date issueDate = new Date();
        Date expireDate = new Date(issueDate.getTime()+(1000*60*60));


        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(secretKey)
                .compact();
        return jwt;
    }
}
