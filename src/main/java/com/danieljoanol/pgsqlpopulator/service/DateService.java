package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import com.danieljoanol.pgsqlpopulator.model.DateType;

public interface DateService {

    public List<String> generateValues(Integer recordsNumber, DateType date);
    
}
