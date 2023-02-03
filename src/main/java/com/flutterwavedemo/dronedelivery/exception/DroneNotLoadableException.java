package com.flutterwavedemo.dronedelivery.exception;

public class DroneNotLoadableException extends RuntimeException {

    public DroneNotLoadableException(String droneSerialNumber, String medicationCode) {
        super("The drone with serial number '" + droneSerialNumber + "' cannot be loaded with item '"
                 + medicationCode + "'");
    }

}
