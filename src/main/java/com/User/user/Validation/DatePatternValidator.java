package com.User.user.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
@Component
public class DatePatternValidator implements ConstraintValidator<ValidDate, String> {

    private String[] patterns;

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.patterns = constraintAnnotation.patterns();
    }

    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext context) {
        if (dateStr == null || dateStr.isEmpty()) {
            return true; // Handle null or empty based on your requirements
        }

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                LocalDate.parse(dateStr, formatter);
                return true; // Valid date found
            } catch (DateTimeParseException e) {
                // Ignore and try the next pattern
            }
        }

        return false; // No valid format found
    }
}

