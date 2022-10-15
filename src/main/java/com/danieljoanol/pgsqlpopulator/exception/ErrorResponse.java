package com.danieljoanol.pgsqlpopulator.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    
    private Timestamp timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(HttpStatus status, String message, String path){

        timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status.value();
        error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
        
    }
    
}
