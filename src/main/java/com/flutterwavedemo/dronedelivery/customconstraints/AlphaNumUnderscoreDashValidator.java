package com.flutterwavedemo.dronedelivery.customconstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AlphaNumUnderscoreDashValidator implements ConstraintValidator<DroneModelConstraint, String> {
    private static final String ALPHANUMERIC_UNDERSCORE_PATTERN = "^[a-zA-Z0-9_-]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(ALPHANUMERIC_UNDERSCORE_PATTERN);
    }
}
