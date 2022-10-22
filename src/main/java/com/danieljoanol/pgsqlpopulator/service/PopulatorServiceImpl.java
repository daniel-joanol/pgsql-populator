package com.danieljoanol.pgsqlpopulator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.GenericType;

@Service
public class PopulatorServiceImpl implements PopulatorService {

    @Autowired
    private ValuesService valuesService;

    @Override
    public String createQuery(String tableName, Integer recordsNumber, List<GenericType> fields) {
        
        List<String> fieldNames = new ArrayList<>();
        Map<String, List<String>> mappedValues = new HashMap<>();

        for (GenericType field : fields) {
            List<String> values = valuesService.generateValues(field, recordsNumber);
            mappedValues.put(field.getName(), values);
        }

        for (GenericType field : fields) {
            fieldNames.add(field.getName());
        }

        String query = "INSERT INTO " + tableName + "(";
        query += String.join(", ", fieldNames) + ")";

        for (int i = 0; i < recordsNumber; i++) {
            query += "\n   VALUES (";
            
            for (String key : fieldNames) {
                List<String> values = mappedValues.get(key);
                query += "'" + values.get(i) + "',";
            }

            query = query.substring(0, query.length() -1) + "),";
        }
        
        query = query.substring(0, query.length() -1) + ";";
        return query;
    }
}
