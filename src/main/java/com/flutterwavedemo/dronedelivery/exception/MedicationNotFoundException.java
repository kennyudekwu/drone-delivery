package com.flutterwavedemo.dronedelivery.exception;

public class MedicationNotFoundException extends RuntimeException {

    public MedicationNotFoundException(String medicationCode) {
        super("The medication code '" + medicationCode + "' does not exist in our records");
    }
    
}