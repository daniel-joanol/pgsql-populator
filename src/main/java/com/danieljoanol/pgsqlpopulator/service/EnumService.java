package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import com.danieljoanol.pgsqlpopulator.model.EnumType;

public interface EnumService {

    public List<String> generateValues(Integer recordsNumber, EnumType enumt);
    
}
