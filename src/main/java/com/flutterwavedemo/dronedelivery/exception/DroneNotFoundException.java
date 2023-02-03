package com.flutterwavedemo.dronedelivery.exception;

public class DroneNotFoundException extends RuntimeException {

    public DroneNotFoundException(String droneSerialNumber) {
        super("The drone with serial number '" + droneSerialNumber + "' does not exist in our records");
    }
    
}