package com.hcl.liberaryManager.util;


import com.hcl.liberaryManager.entity.User;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;


@Component
@Slf4j
public class JwtUtils {
    @Value("${hclorg.app.jwtSecret}")
    private String SECRET_KEY;

    @Value("${hclorg.app.expiration}")
    private int EXPIRATIONMS;

    @Autowired
    private  JwtEncoder encoder ;

    @Autowired
    private JwtDecoder decoder ;

    public boolean validate(String token){
        try{
            decoder.decode(token);
            /*Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);*/
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String  getUsername(String token) {
        return  Arrays
                .stream(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject().split(","))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("invalid token format"));
    }

   /* public String generateToken(User user){
        return  Jwts.builder()
                .setSubject(String.format("%s,%s", user.getUsername(), user.getId()))
                .setIssuer("CodeJava")
                .claim("role",user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATIONMS))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }*/
   public String generateToken(User user){
       JwtClaimsSet claimsSet = JwtClaimsSet.builder()
               .expiresAt(Instant.now().plus(EXPIRATIONMS, ChronoUnit.MILLIS))
               .subject(String.format("%s,%s", user.getUsername(), user.getId()))
               .build();

       return encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
   }
}

