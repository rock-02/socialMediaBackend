package com.example.mediaScocial.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> otherExceptionHandler(
            Exception ue,
            WebRequest req) {

        ErrorDetails error = new ErrorDetails();

        error.setMessage(ue.getMessage());

        error.setError(req.getDescription(false));

        error.setTimeStamp(new Date(System.currentTimeMillis()));

        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }
}
