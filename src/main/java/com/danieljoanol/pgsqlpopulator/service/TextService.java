package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import com.danieljoanol.pgsqlpopulator.model.TextType;

public interface TextService {
 
    public List<String> generateValues(Integer recordsNumber, TextType text);
    
}
