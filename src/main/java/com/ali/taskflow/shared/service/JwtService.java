package com.ali.taskflow.shared.service;

import com.ali.taskflow.user.projection.UserDetailProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpirationMs;
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;
    private final IUserRepository userRepository;

    public JwtService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        this.algorithm=Algorithm.HMAC256(SECRET_KEY);
        this.jwtVerifier=JWT.require(algorithm).build();
    }

    private Algorithm getAlgorithm(){
        return algorithm;
    }

    public String generateToken(UserDetailProjection user){
        return JWT.create()
                .withIssuer("TaskFlow")
                .withSubject(user.getUsername())
                .withClaim("id",user.getId())
                .withClaim("username",user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpirationMs))
                .withIssuedAt(Instant.now())
                .sign(getAlgorithm());
    }

    public String getUsername(String token){
        return jwtVerifier.verify(token).getClaim("username").asString();
    }

    public boolean isTokenValid(String token){
        try{
            jwtVerifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }



}
