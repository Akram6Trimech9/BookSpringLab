package com.example.project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ValidISBN, String> {

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        if (isbn == null) return false;
        return isbn.matches("^\\d{2}-\\d{3}-\\d{3}$");
    }
}
