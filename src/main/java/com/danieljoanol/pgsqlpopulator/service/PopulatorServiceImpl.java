package com.danieljoanol.pgsqlpopulator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;

@Service
public class PopulatorServiceImpl implements PopulatorService {

    @Autowired
    private TextTypesService textTypesService;

    @Override
    public String createQuery(String tableName, Integer recordsNumber, List<GenericType> fields) {
        
        Query queryObj = new Query();
        List<String> fieldNames = new ArrayList<>();
        String query = "";

        for (GenericType field : fields) {
            fieldNames.add(field.getName());
        }
        
        for (int i = 0; i < recordsNumber; i++) {
            
            if (i != 0) {
                query += "\n\n";
            }

            query += "INSERT INTO " + tableName + "(";
            query += String.join(", ", fieldNames) + ") VALUES (";
            queryObj.setQuery(query);

            for (GenericType field : fields) {
                queryObj = textTypesService.addValue(field, queryObj);
            }

            query = queryObj.getQuery();
            query = query.substring(0, query.length() -2) + ");";

        }

        return query;
    }
    
}
