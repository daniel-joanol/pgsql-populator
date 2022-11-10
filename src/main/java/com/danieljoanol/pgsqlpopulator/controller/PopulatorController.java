package com.danieljoanol.pgsqlpopulator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.danieljoanol.pgsqlpopulator.service.PopulatorService;
import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Controller
@RequestMapping("/api/v1/populator")
@Api(value = "Populator Controller", description = "PgSQL Populator Api")
@CrossOrigin
public class PopulatorController {
    
    @Autowired
    private PopulatorService service;

    @Operation(summary = "Create", description = "Creates Query")
    @ApiResponse(responseCode = "200", description = "Created", 
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Error message")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping("/")
    public ResponseEntity<String> createQuery(
            @RequestParam(required = true) String tableName,
            @RequestParam(required = true) Integer recordsNumber,
            @RequestBody(required = true) List<JsonNode> fields
    ) {

        if (recordsNumber <= 0 || recordsNumber > 100) {
            throw new IllegalArgumentException("RecordsNumber has to be bigger than 0 and lesser than 100");
        }

        if (fields.isEmpty()) {
            throw new IllegalArgumentException("Fields can't be empty");
        }

        return ResponseEntity.ok(service.createQuery(tableName, recordsNumber, fields));
    }
}
