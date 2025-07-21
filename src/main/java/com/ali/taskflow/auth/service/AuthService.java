package com.ali.taskflow.auth.service;

import com.ali.taskflow.auth.dto.requests.AuthRequest;
import com.ali.taskflow.auth.dto.responses.TokeResponse;
import com.ali.taskflow.auth.mapper.AuthMapper;
import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.shared.service.JwtService;
import com.ali.taskflow.user.dto.requests.CreateUserRequest;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.projection.UserWithJwtProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final IUserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthService(IUserRepository userRepository, AuthMapper authMapper, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public TokeResponse login(AuthRequest request){
        UserWithJwtProjection user = this.userRepository.findUserWithJwtByUsername(request.getUsername())
                .orElseThrow(()->new GlobalException("Username or Password wrong", HttpStatus.BAD_GATEWAY));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        return new TokeResponse(jwtService.generateToken(user));
    }

    public CreateUserRequest register(CreateUserRequest request){
        User user = this.authMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        this.userRepository.save(user);
        return request;
    }
}
