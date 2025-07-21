package com.ali.taskflow.user.rule;

import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRule {

    private final IUserRepository userRepository;

    public UserRule(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
