package com.danieljoanol.pgsqlpopulator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.danieljoanol.pgsqlpopulator.model.Chars;
import com.danieljoanol.pgsqlpopulator.model.GenericType;

import net.datafaker.Faker;

@Service
public class TextTypeServiceImpl implements TextTypesService {

    Faker faker = new Faker(new Locale("en-US"));
    Random random = new Random();

    @Override
    public List<String> generateValues(GenericType field, Integer recordsNumber) {
        
        switch (field.getType()) {

            case CHAR:
                field.setLength(null);
                return generateValue(field.getType().toString(), field, recordsNumber);

            case ENUM:
                field.setLength(null);
                return generateValue(field.getType().toString(), field, recordsNumber);

            case VARCHAR, TEXT:
                return generateValue(field.getVarcharType().toString(), field, recordsNumber);
        
            default:
                return null;
        }
        
    }

    public List<String> generateValue(String type, GenericType field, Integer recordsNumber) {
        
        List<String> values = new ArrayList<>();
        String value;

        switch (type) {

            case "CHAR":
                
                if (recordsNumber < values.size()) {
                    values = Arrays.asList(Chars.array);
                } else {
                    List<String> valuesA = Arrays.asList(Chars.array);
                    List<String> valuesB = Arrays.asList(Chars.array);
                    values = Stream.concat(valuesA.stream(), valuesB.stream())
                            .collect(Collectors.toList());
                }

                break;

            case "FIRST_NAME":
                values = faker.collection(
                        () -> faker.name().firstName())
                    .len(recordsNumber)
                    .generate();
                break;

            case "LAST_NAME":
                values = faker.collection(
                    () -> faker.name().lastName())
                .len(recordsNumber)
                .generate();
                break;

            case "FULL_NAME":
                values = faker.collection(
                    () -> faker.name().fullName())
                .len(recordsNumber)
                .generate();
                break;

            case "ADDRESS":
                values = faker.collection(
                    () -> faker.address().fullAddress())
                .len(recordsNumber)
                .generate();
                break;

            case "COMPANY":
                values = faker.collection(
                    () -> faker.company().name())
                .len(recordsNumber)
                .generate();
                break;

            case "ID_NUMBER":
                values = faker.collection(
                    () -> faker.idNumber().valid())
                .len(recordsNumber)
                .generate();
                break;

            case "PHONE_NUMBER":
                values = faker.collection(
                    () -> faker.phoneNumber().cellPhone())
                .len(recordsNumber)
                .generate();
                break;

            case "ENUM":

                if (field.getItems() == null) {
                    throw new IllegalArgumentException("Items can't be null");
                }

                String[] items = field.getItems();
                Integer n = random.nextInt(items.length);

                for (int i = 0; i < recordsNumber; i++) {
                    value = items[n];
                    values.add(value);
                }
                
                break;

        }

        if (field.getLength() != null ) {
            for (int i = 0; i < recordsNumber; i++) {
                value = values.get(i);
    
                if (value.length() > field.getLength()) {
                    value = value.substring(0, field.getLength());
                }
                
            }
        }

        return values;
    }

}
