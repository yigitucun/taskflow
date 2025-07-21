package com.ali.taskflow.user.service;

import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.user.projection.UserDetailProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;

    public CustomUserDetailService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailProjection user = this.userRepository.findUserWithUserDetail(username)
                .orElseThrow(()->new GlobalException("Username or password wrong",HttpStatus.UNAUTHORIZED));
        return new User(user.getUsername(),user.getPassword(), List.of());
    }
}
