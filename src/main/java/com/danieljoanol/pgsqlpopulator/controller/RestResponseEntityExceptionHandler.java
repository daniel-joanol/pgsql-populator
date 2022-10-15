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
import com.danieljoanol.pgsqlpopulator.model.enumarator.FieldType;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        //TODO: add a message for VarcharType

        System.err.println(ex.getMessage());

        FieldType[] types = FieldType.values();
        String[] typeNames = new String[ types.length ];
        
        for (int i = 0; i < types.length; i++) {
            typeNames[i] = types[i].toString();
        }

        String body = "The valid field types are: " + String.join(", ", typeNames);
        String[] uri = request.getDescription(false).split("uri=");
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, body, uri[1]);
        
        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { InvalidParameterException.class })
    protected ResponseEntity<Object> handleInvalidParameterException(RuntimeException ex, WebRequest request) {

        String body = ex.getMessage();
        String[] uri = request.getDescription(false).split("uri=");
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, body, uri[1]);

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
}
