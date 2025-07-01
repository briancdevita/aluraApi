package com.voll.api.infra.exception.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {


    @Value("${api.security.jwt.secret-key}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Api Voll")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw  new RuntimeException("Error generating token", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
       try {
           var algoritmo = Algorithm.HMAC256(secret);
              return JWT.require(algoritmo)
                     .withIssuer("Api Voll")
                     .build()
                     .verify(tokenJWT)
                     .getSubject();
         } catch (JWTVerificationException e) {
              throw new RuntimeException("Invalid token", e);
       }
       }
}
