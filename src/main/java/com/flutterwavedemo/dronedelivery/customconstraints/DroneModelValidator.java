package com.flutterwavedemo.dronedelivery.customconstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class DroneModelValidator implements ConstraintValidator<DroneModelConstraint, String> {
    List<String> modelTypes = Arrays.asList(
            "Lightweight", "Middleweight",
            "Cruiseweight", "Heavyweight"
    );
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        for (String modelType : modelTypes) {
            if (value.equals(modelType)) return true;
        }
        return false;
    }
}
