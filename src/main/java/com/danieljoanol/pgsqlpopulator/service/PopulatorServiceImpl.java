package com.danieljoanol.pgsqlpopulator.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        
        Map<String, String> mappedNames = new LinkedHashMap<>();
        Map<String, List<String>> mappedValues = new HashMap<>();

        for (GenericType field : fields) {

            switch (field.getType()) {

                case VARCHAR:
                    if (field.getVarcharType() == null) {
                        throw new IllegalArgumentException("varcharType can't be null for fieldType VARCHAR");
                    }

                    mappedNames.put(field.getName(), field.getVarcharType().name());
                    break;
                
                default:
                    mappedNames.put(field.getName(), field.getType().name());
                    break;
            }
        }

        for (GenericType field : fields) {
            List<String> values = valuesService.generateValues(field, recordsNumber);
            mappedValues.put(field.getName(), values);
        }

        String query = "INSERT INTO " + tableName + "(";
        query += String.join(", ", mappedNames.keySet()) + ")";

        for (int i = 0; i < recordsNumber; i++) {
            query += "\n   VALUES (";
            
            for (String key : mappedNames.keySet()) {
                List<String> values = mappedValues.get(key);
                String fieldType = mappedNames.get(key);

                switch (fieldType) {

                    case "SMALL_INT", "INTEGER", "BIG_INT", "MONEY", "BOOLEAN":
                        query += " " + values.get(i) + ",";
                        break;

                    default:
                        query += " '" + values.get(i) + "',";
                }
            }

            query = query.substring(0, query.length() -1) + " ),";
        }
        
        query = query.substring(0, query.length() -1) + ";";
        return query;
    }
}
