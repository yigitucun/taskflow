package com.ali.taskflow.auth.controller;

import com.ali.taskflow.auth.dto.requests.AuthRequest;
import com.ali.taskflow.auth.dto.requests.RefreshTokenRequest;
import com.ali.taskflow.auth.entity.RefreshToken;
import com.ali.taskflow.auth.repository.IRefreshTokenRepository;
import com.ali.taskflow.auth.service.AuthService;
import com.ali.taskflow.auth.service.RefreshTokenService;
import com.ali.taskflow.auth.service.TokenService;
import com.ali.taskflow.shared.service.JwtService;
import com.ali.taskflow.user.dto.requests.CreateUserRequest;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.projection.UserDetailProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;
    private final IRefreshTokenRepository tokenRepository;
    private final RefreshTokenService refreshTokenService;


    public AuthController(AuthService authService, TokenService tokenService, IRefreshTokenRepository tokenRepository, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
        this.tokenRepository = tokenRepository;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        this.refreshTokenService.validateRefreshToken(request.getRefreshToken());
        RefreshToken token = this.tokenRepository.findByToken(request.getRefreshToken());
        return ResponseEntity.status(200).body(tokenService.createAuthToken(token.getUser().getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.status(200).body(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.status(201).body(authService.register(request));
    }

}
