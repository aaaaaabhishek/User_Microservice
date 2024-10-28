package com.User.user.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> getException(InvalidException ie){
        return new ResponseEntity<>(ie.getMessage(), ie.getHttpStatus());
    }
}
