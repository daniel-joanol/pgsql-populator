package com.danieljoanol.pgsqlpopulator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.text.Char;
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

        query = "INSERT INTO " + tableName + "(";
        query += String.join(", ", fieldNames) + ") VALUES ('";
        queryObj.setQuery(query);

        for (GenericType field : fields) {
            queryObj = addValue(field, queryObj);
        }

        query = queryObj.getQuery();
        query = query.substring(-2) + ");";
        
        return query;
    }

    public Query addValue(GenericType field, Query queryObj) {

        switch (field.getClass().getName()) {
            case "Char":
            queryObj = textTypesService.addCharValue((Char) field, queryObj);
                break;

            default:
                //TODO: create exception for Field type no valid
                break;
        }

        return queryObj;
    }
    
}
