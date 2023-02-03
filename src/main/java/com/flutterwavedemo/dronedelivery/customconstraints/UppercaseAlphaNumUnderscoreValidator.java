package com.flutterwavedemo.dronedelivery.customconstraints;

import com.flutterwavedemo.dronedelivery.customconstraints.DroneModelConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UppercaseAlphaNumUnderscoreValidator implements ConstraintValidator<DroneModelConstraint, String> {
    private static final String ALPHANUMERIC_UNDERSCORE_PATTERN = "^[A-Z0-9_]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(ALPHANUMERIC_UNDERSCORE_PATTERN);
    }
}
