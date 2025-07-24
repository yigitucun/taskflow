package com.ali.taskflow.shared.annotation.abstracts;

import com.ali.taskflow.shared.annotation.concretes.EnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface ValidEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "invalid enum value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
