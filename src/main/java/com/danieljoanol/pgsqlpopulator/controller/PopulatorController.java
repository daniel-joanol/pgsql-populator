package com.danieljoanol.pgsqlpopulator.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.service.PopulatorService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api/v1/populator")
@Tag(name = "Populator Controller", description = "PgSQL Populator Api")
public class PopulatorController {
    
    @Autowired
    private PopulatorService service;

    @Operation(summary = "Create", description = "Creates Query")
    @ApiResponse(responseCode = "201", description = "Created", 
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Error message")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping("/")
    public ResponseEntity<String> createQuery(
            @RequestParam(required = true) String tableName,
            @RequestParam(required = true) Integer recordsNumber,
            @RequestBody(required = true) List<GenericType> fields
    ) {

        if (recordsNumber <= 0) {
            return ResponseEntity.badRequest().body("recordsNumber has to be bigger than 0");
        }

        if (fields.isEmpty()) {
            return ResponseEntity.badRequest().body("Fields can't be empty");
        }

        return ResponseEntity.ok(service.createQuery(tableName, recordsNumber, fields));
    }
}
