package com.fastfeet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private String secret = "FASFADSGASFHSADFHSDXFHDSFH";
    private Long time = 600000L;

    private Algorithm algorithm = Algorithm.HMAC512(secret.getBytes());

    public String generateToken(String username) {

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .sign(algorithm);
    }

    public String tokenValid(String token) {
        var decodedJWT = JWT.require(algorithm).build().verify(token);
        if (decodedJWT != null) {
            var username = decodedJWT.getSubject();
            if (username != null) {
                return decodedJWT.getSubject();
            }
        }
        return null;
    }

}
