package com.ali.taskflow.shared.annotation.abstracts;

import com.ali.taskflow.shared.annotation.concretes.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "username is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
