package com.ali.taskflow.shared.annotation.concretes;

import com.ali.taskflow.shared.annotation.abstracts.UniqueEmail;
import com.ali.taskflow.user.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    private final IUserRepository userRepository;

    public UniqueEmailValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userRepository.existsByEmail(email);
    }
}
