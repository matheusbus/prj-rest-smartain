package br.udesc.smartain.restsmartainproject.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException exc) {

       // Create an ErrorResponse
        ErrorResponse error = new ErrorResponse();

        // Set values to attributes
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<ErrorResponse> handleException(Exception exc) {

        // Create an ErrorResponse
        ErrorResponse error = new ErrorResponse();

        // Set values to attributes
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }


}
