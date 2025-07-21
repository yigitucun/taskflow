package com.ali.taskflow.auth.service;

import com.ali.taskflow.auth.mapper.AuthMapper;
import com.ali.taskflow.user.dto.requests.CreateUserRequest;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final IUserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder encoder;

    public AuthService(IUserRepository userRepository, AuthMapper authMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.encoder = encoder;
    }

    public CreateUserRequest register(CreateUserRequest request){
        User user = this.authMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        this.userRepository.save(user);
        return request;
    }
}
