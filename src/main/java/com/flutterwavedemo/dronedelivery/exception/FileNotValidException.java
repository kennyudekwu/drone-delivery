package com.flutterwavedemo.dronedelivery.exception;

public class FileNotValidException extends RuntimeException {
    public FileNotValidException() {
        super("The file is not valid");
    }
}
