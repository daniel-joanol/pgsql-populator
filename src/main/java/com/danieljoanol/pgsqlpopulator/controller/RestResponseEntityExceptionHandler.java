package com.danieljoanol.pgsqlpopulator.controller;

import java.security.InvalidParameterException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.danieljoanol.pgsqlpopulator.exception.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String body = "The error is probably caused by an invalid option for ENUM or a badly formatted array";
        String[] uri = request.getDescription(false).split("uri=");
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, body, uri[1]);
        
        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { InvalidParameterException.class, IllegalArgumentException.class })
    protected ResponseEntity<Object> handleInvalidParameterException(RuntimeException ex, WebRequest request) {

        String body = ex.getMessage();
        String[] uri = request.getDescription(false).split("uri=");
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, body, uri[1]);

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
}
