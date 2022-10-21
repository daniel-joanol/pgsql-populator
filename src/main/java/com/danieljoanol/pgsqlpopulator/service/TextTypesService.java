package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import com.danieljoanol.pgsqlpopulator.model.GenericType;

public interface TextTypesService {

    public List<String> generateValues(GenericType field, Integer recordsNumber);

}
