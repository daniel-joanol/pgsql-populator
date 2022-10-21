package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import com.danieljoanol.pgsqlpopulator.model.GenericType;

public interface PopulatorService {
    
    public String createQuery(String tableName, Integer recordsNumber, List<GenericType> fields);
}
