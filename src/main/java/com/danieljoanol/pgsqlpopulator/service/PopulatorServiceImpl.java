package com.danieljoanol.pgsqlpopulator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.DateType;
import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.EnumType;
import com.danieljoanol.pgsqlpopulator.model.TextType;
import com.danieljoanol.pgsqlpopulator.model.enumarator.FieldType;
import com.danieljoanol.pgsqlpopulator.model.enumarator.VarcharType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PopulatorServiceImpl implements PopulatorService {

    @Autowired
    private TextService textService;

    @Autowired
    private EnumService enumService;

    @Autowired
    private DateService dateService;

    @Autowired
    private GenericService genericService;

    @Override
    public String createQuery(String tableName, Integer recordsNumber, List<JsonNode> fields) {
        
        ObjectMapper mapper = new ObjectMapper();
        List<String> values;
        Map<String, String> mappedNames = new LinkedHashMap<>();
        Map<String, List<String>> mappedValues = new HashMap<>();
        String query = "INSERT INTO " + tableName + "(";

        for (JsonNode field : fields) {

            if (field.has("type")) {

                String type = field.get("type").toString();
                type = type.substring(1, type.length() -1);
            
                switch (type) {

                    case "TEXT", "VARCHAR":
                        if (!field.has("varcharType")) {
                            throw new IllegalArgumentException("varcharType can't be null for type VARCHAR");
                        } 

                        String varcharTypeName = field.get("varcharType").toString();
                        varcharTypeName = varcharTypeName.substring(1, varcharTypeName.length() -1);
                        switch (varcharTypeName) {

                            case "FIRST_NAME", "LAST_NAME", "FULL_NAME", "ADDRESS", "COMPANY", "ID_NUMBER", "PHONE_NUMBER":
                                break;

                            default:
                                List<String> typeVarcharList = new ArrayList<>();
                                for (VarcharType typeName : VarcharType.values()) {
                                    typeVarcharList.add(typeName.toString());
                                }
                                String errorMessage = "The valid varcharTypes are " + 
                                        String.join(", ", typeVarcharList);
                                throw new IllegalArgumentException(errorMessage);
                        }

                        TextType text = mapper.convertValue(field, TextType.class);
                        values = textService.generateValues(recordsNumber, text);
                        mappedValues.put(text.getName(), values);
                        mappedNames.put(text.getName(), text.getType());
                        break;

                    case "DATE", "TIME", "TIMESTAMP":
                        if (!field.has("startDate") || !field.has("endDate")) {
                            throw new IllegalArgumentException(
                                    "startDate or endDate can't be null for types DATE, TIME and TIMESTAMP");
                        }

                        DateType date = mapper.convertValue(field, DateType.class);
                        values = dateService.generateValues(recordsNumber, date);
                        mappedValues.put(date.getName(), values);
                        mappedNames.put(date.getName(), date.getType());
                        break;

                    case "ENUM":
                        if (!field.has("items")) {
                            throw new IllegalArgumentException("items can't be null for type ENUM");
                        }

                        EnumType enumt = mapper.convertValue(field, EnumType.class);
                        values = enumService.generateValues(recordsNumber, enumt);
                        mappedValues.put(enumt.getName(), values);
                        mappedNames.put(enumt.getName(), enumt.getType());
                        break;

                    case "CHAR", "BOOLEAN", "UUID", "SMALLINT", "INTEGER", "BIG_INT", "MONEY":
                        GenericType generic = mapper.convertValue(field, GenericType.class);
                        values = genericService.generateValues(recordsNumber, generic);
                        mappedValues.put(generic.getName(), values);
                        mappedNames.put(generic.getName(), generic.getType());
                        break;

                    default:
                        List<String> typeList = new ArrayList<>();
                        for (FieldType typeName : FieldType.values()) {
                            typeList.add(typeName.toString());
                        }
                        String errorMessage = "The valid types are " + 
                                String.join(", ", typeList);
                        throw new IllegalArgumentException(errorMessage);
                }

            } else {
                throw new IllegalArgumentException("Type can't be null");
            }
    
            query += String.join(", ", mappedNames.keySet()) + ")";
    
            for (int i = 0; i < recordsNumber; i++) {
                query += "\n   VALUES (";
                
                for (String key : mappedNames.keySet()) {
                    values = mappedValues.get(key);
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
        }

        return query.substring(0, query.length() -1) + ";";
    }
    
}
