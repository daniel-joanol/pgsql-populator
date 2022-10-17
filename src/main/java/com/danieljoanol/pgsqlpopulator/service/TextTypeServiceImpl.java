package com.danieljoanol.pgsqlpopulator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.jeasy.random.EasyRandom;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;
import com.github.javafaker.Faker;

@Service
public class TextTypeServiceImpl implements TextTypesService {

    Faker faker = new Faker(new Locale("en-US"));
    Random random = new Random();

    @Override
    public Query addValue(GenericType field, Query queryObj) {
        
        switch (field.getType()) {

            case CHAR:
                return generateValue(queryObj, field.getType().toString(), field);

            case ENUM:
                field.setUnique(false);
                field.setLength(null);
                return generateValue(queryObj, field.getType().toString(), field);

            case VARCHAR, TEXT:
                return generateValue(queryObj, field.getVarcharType().toString(), field);
        
            default:
                return null;
        }
        
    }

    public Query generateValue(Query queryObj, String type, GenericType field) {

        final String fieldName = field.getName();
        boolean isPresent = true;
        String value = generateValue(type, field);
        
        if (field.getUnique()) {

            if (queryObj.getUniqueValues().get(fieldName) != null) {

                do {

                    if (queryObj.getUniqueValues().get(fieldName).contains(value)) {
                        value = generateValue(type, field);
                    } else {
                        isPresent = false;
                    }

                } while (isPresent);

                queryObj.getUniqueValues().get(fieldName).add(value);

            } else {

                List<String> values = new ArrayList<>();
                values.add(value);
                queryObj.getUniqueValues().put(fieldName, values);
            }
        }

        return addFinalValue(value, queryObj);
    }

    public String generateValue(String type, GenericType field) {
        
        String value = "";

        switch (type) {

            case "CHAR":
                final EasyRandom generator = new EasyRandom();
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
                value = faker.address().fullAddress();
                break;

            case "COMPANY":
                value = faker.company().name();
                break;

            case "ID_NUMBER":
                value = faker.idNumber().valid();
                break;

            case "PHONE_NUMBER":
                value = faker.phoneNumber().phoneNumber();
                break;

            case "ENUM":

                if (field.getItems() == null) {
                    throw new IllegalArgumentException("Items can't be null");
                }

                String[] items = field.getItems();
                Integer n = random.nextInt(items.length);
                value = items[n];
                break;

        }

        if (field.getLength() != null) {
            value = value.substring(0, field.getLength());
        }

        return value;

    }

    public Query addFinalValue(String value, Query queryObj) {
        
        String query = queryObj.getQuery();
        query += "'" + value + "', ";
        queryObj.setQuery(query);

        return queryObj;
    }
}
