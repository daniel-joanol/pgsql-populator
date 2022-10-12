package com.danieljoanol.pgsqlpopulator.model.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Query {

    public String query;
    public Map<String, List<String>> uniqueValues = new HashMap<>();
    
}
