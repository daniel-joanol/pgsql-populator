package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public interface PopulatorService {
    
    public String createQuery(String tableName, Integer recordsNumber, List<JsonNode> fields);
}
