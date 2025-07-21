package com.ali.taskflow.shared.annotation.abstracts;

import com.ali.taskflow.shared.annotation.concretes.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "email is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
