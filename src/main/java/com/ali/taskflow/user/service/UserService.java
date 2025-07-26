package com.ali.taskflow.user.service;

import com.ali.taskflow.user.projection.ListUserProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ListUserProjection> getAll(){
        return this.userRepository.findAllBy();
    }
}
