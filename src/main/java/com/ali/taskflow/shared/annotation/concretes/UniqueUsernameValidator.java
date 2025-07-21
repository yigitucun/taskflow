package com.ali.taskflow.shared.annotation.concretes;

import com.ali.taskflow.shared.annotation.abstracts.UniqueUsername;
import com.ali.taskflow.user.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator  implements ConstraintValidator<UniqueUsername,String> {

    private final IUserRepository userRepository;

    public UniqueUsernameValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userRepository.existsByUsername(username);
    }
}
