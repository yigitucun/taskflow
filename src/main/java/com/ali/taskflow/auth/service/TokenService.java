package com.ali.taskflow.auth.service;

import com.ali.taskflow.auth.dto.responses.TokenResponse;
import com.ali.taskflow.shared.service.JwtService;
import com.ali.taskflow.user.projection.UserDetailProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import com.ali.taskflow.user.rule.UserRule;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final IUserRepository userRepository;
    private final UserRule userRule;


    public TokenService(JwtService jwtService, RefreshTokenService refreshTokenService, IUserRepository userRepository, UserRule userRule) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.userRule = userRule;
    }

    public TokenResponse createAuthToken(long userId){
        this.userRule.checkIfUserId(userId);
        UserDetailProjection user = this.userRepository.findUserWithUserDetailById(userId);
        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.generateRefreshToken(user);
        return new TokenResponse(accessToken,refreshToken);
    }

}
