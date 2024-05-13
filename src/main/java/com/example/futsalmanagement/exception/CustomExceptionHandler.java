package com.example.futsalmanagement.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({Exception.class})
    public String handleException(Exception es, final WebRequest wr) {
        return es.getMessage();
    }
}
