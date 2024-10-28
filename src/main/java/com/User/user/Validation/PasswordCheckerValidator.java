package com.User.user.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordCheckerValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&  // At least one uppercase letter
                password.matches(".*[a-z].*") &&  // At least one lowercase letter
                password.matches(".*[0-9].*") &&  // At least one digit
                password.matches(".*[@#$%^&+=!].*"); // At least one special character
    }
}

