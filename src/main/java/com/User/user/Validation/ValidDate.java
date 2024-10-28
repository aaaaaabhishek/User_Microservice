package com.User.user.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatePatternValidator.class)
public @interface ValidDate {
    String message() default "Invalid date format"; // Default error message
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] patterns(); // To accept multiple date patterns
}