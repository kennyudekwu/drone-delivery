package com.flutterwavedemo.dronedelivery.customconstraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class DroneStateValidatorClass implements ConstraintValidator<DroneStateConstraint, String> {
    List<String> modelStates = Arrays.asList(
            "IDLE", "LOADING",
            "LOADED", "DELIVERING",
            "DELIVERED", "RUNNING"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        for (String modelState : modelStates) {
            if (value.equals(modelState)) return true;
        }
        return false;
    }
}
