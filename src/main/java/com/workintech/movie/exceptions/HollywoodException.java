package com.workintech.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HollywoodException {
    @ExceptionHandler
    public ResponseEntity<HollywoodErrorResponse> handleException(MovieException exception) {

        HollywoodErrorResponse response = new HollywoodErrorResponse(exception.getCode().value(),
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, exception.getCode());
    }

    @ExceptionHandler
    public ResponseEntity<HollywoodErrorResponse> handleException(Exception exception) {
        //log.error(exception.getMessage());
        HollywoodErrorResponse response = new HollywoodErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
