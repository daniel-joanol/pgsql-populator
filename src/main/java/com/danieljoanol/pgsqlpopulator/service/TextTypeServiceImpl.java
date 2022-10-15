package com.danieljoanol.pgsqlpopulator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jeasy.random.EasyRandom;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;

@Service
public class TextTypeServiceImpl implements TextTypesService {

    Faker faker = new Faker(new Locale("en-US"));

    @Override
    public Query addValue(GenericType field, Query queryObj) {

        final String typeName = field.getName();
        
        switch (field.getType()) {

            case CHAR:
                return generateValue(queryObj, field.getUnique(), typeName, "CHAR");

            case VARCHAR:
                return generateValue(queryObj, field.getUnique(), typeName, field.getVarcharType().toString());
        
            default:
                return null;
        }
        
    }

    public String generateValue(String type) {
        
        final EasyRandom generator = new EasyRandom();
        String value = "";

        switch (type) {

            case "CHAR":
                value = generator.nextObject(Character.class).toString();
                break;

            case "FIRST_NAME":
                value = faker.name().firstName();
                break;

            case "LAST_NAME":
                value = faker.name().lastName();
                break;

            case "FULL_NAME":
                value = faker.name().fullName();
                break;

            case "ADDRESS":
                Address address = faker.address();
                value = address.toString();
                break;

            case "COMPANY":
                value = faker.company().name();
                break;

            case "COLOR":
                value = faker.color().name();
                break;

            case "ID_NUMBER":
                value = faker.idNumber().toString();
                break;

            case "PHONE_NUMBER":
                value = faker.phoneNumber().phoneNumber();
                break;

        }

        return value;

    }

    public Query generateValue(Query queryObj, Boolean unique, String typeName, String type) {

        String value = generateValue(type);
        boolean isPresent = true;

        if (unique) {

            if (queryObj.getUniqueValues().get(typeName) != null) {

                do {

                    if (queryObj.getUniqueValues().get(typeName).contains(value)) {
                        value = generateValue(type);
                    } else {
                        isPresent = false;
                    }

                } while (isPresent);

                queryObj.getUniqueValues().get(typeName).add(value);

            } else {

                List<String> values = new ArrayList<>();
                values.add(value);
                queryObj.getUniqueValues().put(typeName, values);
            }
        }

        return addFinalValue(value, queryObj);
    }

    public Query addFinalValue(String value, Query queryObj) {
        
        String query = queryObj.getQuery();
        query += "'" + value + "', ";
        queryObj.setQuery(query);

        return queryObj;
    }
}
