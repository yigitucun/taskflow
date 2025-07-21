package com.ali.taskflow.auth.service;

import com.ali.taskflow.auth.dto.requests.AuthRequest;
import com.ali.taskflow.auth.dto.responses.TokenResponse;
import com.ali.taskflow.auth.mapper.AuthMapper;

import com.ali.taskflow.user.dto.requests.CreateUserRequest;
import com.ali.taskflow.user.entity.SecurityUser;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final IUserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthService(IUserRepository userRepository, AuthMapper authMapper, PasswordEncoder encoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public TokenResponse login(AuthRequest request){
         Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        return this.tokenService.createAuthToken(user.getUser().getId());
    }

    public CreateUserRequest register(CreateUserRequest request){
        User user = this.authMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        this.userRepository.save(user);
        return request;
    }
}
