package com.flutterwavedemo.dronedelivery;

import com.flutterwavedemo.dronedelivery.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DroneNotFoundException.class)
    public ResponseEntity<Object> handleDroneNotFoundException(DroneNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> handleMedicationNotFoundException(MedicationNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> handleDroneNotLoadableException(DroneNotLoadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleFileNotValidException(FileNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
}
