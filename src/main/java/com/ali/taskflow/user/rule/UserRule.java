package com.ali.taskflow.user.rule;

import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.user.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserRule {

    private final IUserRepository userRepository;

    public UserRule(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkIfUserId(long userId){
        if (!this.userRepository.existsById(userId))
            throw new GlobalException("User not found", HttpStatus.NOT_FOUND);
    }


}
