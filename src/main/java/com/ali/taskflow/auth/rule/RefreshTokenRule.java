package com.ali.taskflow.auth.rule;

import com.ali.taskflow.auth.repository.IRefreshTokenRepository;
import com.ali.taskflow.shared.exception.globalException.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenRule {
    private final IRefreshTokenRepository refreshTokenRepository;

    public RefreshTokenRule(IRefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
    public void checkIfRefreshToken(String token){
        if (!this.refreshTokenRepository.existsByToken(token))
            throw new GlobalException("token not found", HttpStatus.NOT_FOUND);
    }
}
