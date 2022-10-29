package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import com.danieljoanol.pgsqlpopulator.model.GenericType;

public interface GenericService {
    
    public List<String> generateValues(Integer recordsNumber, GenericType generic);

}
