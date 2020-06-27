package com.fastfeet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fastfeet.GlobalVariables;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JWTUtil {

    private Algorithm algorithm = Algorithm.HMAC512(GlobalVariables.secret.getBytes());

    public String generateToken(String username) {

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + GlobalVariables.time))
                .sign(algorithm);
    }

    public String tokenValid(String token) throws TokenExpiredException {
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
