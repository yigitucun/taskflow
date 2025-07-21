package com.ali.taskflow.auth.service;

import com.ali.taskflow.auth.entity.RefreshToken;
import com.ali.taskflow.auth.repository.IRefreshTokenRepository;
import com.ali.taskflow.auth.rule.RefreshTokenRule;
import com.ali.taskflow.user.projection.UserDetailProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final IUserRepository userRepository;
    private final IRefreshTokenRepository refreshTokenRepository;
    @Value("${jwt.refresh-token.expiration}")
    private Long refreshTokenDuration;
    private final RefreshTokenRule refreshTokenRule;

    public RefreshTokenService(IUserRepository userRepository, IRefreshTokenRepository refreshTokenRepository, RefreshTokenRule refreshTokenRule) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenRule = refreshTokenRule;
    }

    public String generateRefreshToken(UserDetailProjection user){
        this.refreshTokenRepository.deleteByUserId(user.getId());
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDuration));
        refreshToken.setUser(userRepository.getReferenceById(user.getId()));
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public void validateRefreshToken(String token){
        this.refreshTokenRule.checkIfRefreshToken(token);
    }



}
