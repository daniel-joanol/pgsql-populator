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
                query += "\n";
            }

            query += "INSERT INTO " + tableName + "(";
            query += String.join(", ", fieldNames) + ") VALUES (";
            queryObj.setQuery(query);

            for (GenericType field : fields) {
                queryObj = addValue(field, queryObj);
            }

            query = queryObj.getQuery();
            query = query.substring(0, query.length() -2) + ");";

        }

        return query;
    }

    public Query addValue(GenericType field, Query queryObj) {

        switch (field.getType()) {
            case CHAR:
                queryObj = textTypesService.addCharValue(field, queryObj);
                break;

            case ENUM:
                queryObj = textTypesService.addEnumValue(field, queryObj);
                break;

            case LONGTEXT:
                queryObj = textTypesService.addLongTextValue(field, queryObj);
                break;

            case MEDIUMTEXT:
                queryObj = textTypesService.addMediumTextValue(field, queryObj);
                break;

            case SET:
                queryObj = textTypesService.addSetValue(field, queryObj);
                break;

            case TEXT:
                queryObj = textTypesService.addTextValue(field, queryObj);
                break;

            case TINYTEXT:
                queryObj = textTypesService.addTinyTextValue(field, queryObj);
                break;

            case VARCHAR:
                queryObj = textTypesService.addVarcharValue(field, queryObj);
                break;
        }

        return queryObj;
    }
    
}
