package com.flutterwavedemo.dronedelivery.customconstraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlphaNumUnderscoreDashValidator.class)
public @interface AlphaNumUnderscoreDashConstraint {

    String message() default "Invalid Data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
