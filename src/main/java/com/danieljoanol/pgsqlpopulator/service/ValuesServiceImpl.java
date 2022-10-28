package com.danieljoanol.pgsqlpopulator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.enumarator.FieldType;
import com.danieljoanol.pgsqlpopulator.util.Booleans;
import com.danieljoanol.pgsqlpopulator.util.Chars;
import com.danieljoanol.pgsqlpopulator.util.Dates;
import com.danieljoanol.pgsqlpopulator.util.Numbers;
import com.danieljoanol.pgsqlpopulator.util.UUIDs;
import com.danieljoanol.pgsqlpopulator.util.Varchars;

import net.datafaker.Faker;

@Service
public class ValuesServiceImpl implements ValuesService {

    Faker faker = new Faker(new Locale("en-US"));

    @Override
    public List<String> generateValues(GenericType field, Integer recordsNumber) {

        List<FieldType> lengthNull = List.of(FieldType.CHAR, FieldType.SMALL_INT, FieldType.INTEGER, 
                FieldType.BIG_INT, FieldType.MONEY, FieldType.BOOLEAN, FieldType.UUID);

        List<FieldType> dateType = List.of(FieldType.DATE, FieldType.TIME, FieldType.TIMESTAMP);

        if (lengthNull.contains(field.getType())) {
            field.setLength(null);
            return generateValue(field.getType().toString(), field, recordsNumber);
        }

        if (dateType.contains(field.getType())) {
            if (field.getStartDate() == null || field.getEndDate() == null) {
                throw new IllegalArgumentException("startDate or endDate can't be null for types DATE, TIME and TIMESTAMP");
            }

            field.setLength(null);
            return generateValue(field.getType().toString(), field, recordsNumber);
        }

        if (field.getType() == FieldType.ENUM) {
            field.setLength(null);
            if (field.getItems() == null) {
                throw new IllegalArgumentException("Items can't be null");
            }

            return generateValue(field.getType().toString(), field, recordsNumber);
        }

        return generateValue(field.getVarcharType().name(), field, recordsNumber);
    }

    private List<String> generateValue(String type, GenericType field, Integer recordsNumber) {
        
        List<String> strValues = new ArrayList<>();
        String strValue;
        
        switch (type) {

            case "CHAR":
                strValues = Chars.generateChars(recordsNumber);
                break;

            case "FIRST_NAME":
                strValues = Varchars.generateFirstNames(faker, recordsNumber);
                break;

            case "LAST_NAME":
                strValues = Varchars.generateLastNames(faker, recordsNumber);
                break;

            case "FULL_NAME":
                strValues = Varchars.generateFullNames(faker, recordsNumber);
                break;

            case "ADDRESS":
                strValues = Varchars.generateAddresses(faker, recordsNumber);
                break;

            case "COMPANY":
                strValues = Varchars.generateCompanies(faker, recordsNumber);
                break;

            case "ID_NUMBER":
                strValues = Varchars.generateIdNumbers(faker, recordsNumber);
                break;

            case "PHONE_NUMBER":
                strValues = Varchars.generatePhoneNumbers(faker, recordsNumber);
                break;

            case "ENUM":
                strValues = Varchars.generateEnums(faker, recordsNumber, field.getItems());
                break;

            case "SMALL_INT":
                strValues = Numbers.generateSmallInts(faker, recordsNumber);
                break;

            case "INTEGER":
                strValues = Numbers.generateIntegers(faker, recordsNumber);
                break;

            case "BIG_INT":
                strValues = Numbers.generateBigInts(faker, recordsNumber);
                break;

            case "MONEY":
                strValues = Numbers.generateBigInts(faker, recordsNumber);
                break;

            case "BOOLEAN":
                strValues = Booleans.createBooleans(faker, recordsNumber);
                break;

            case "DATE":
                strValues = Dates.generateDates(faker, type, field, recordsNumber);
                break;

            case "TIME":
                strValues = Dates.generateDates(faker, type, field, recordsNumber);
                break;

            case "TIMESTAMP":
                strValues = Dates.generateDates(faker, type, field, recordsNumber);
                break;

            case "UUID":
                strValues = UUIDs.createUUIDs(recordsNumber);
                break;

        }

        if (field.getLength() != null ) {
            for (int i = 0; i < recordsNumber; i++) {
                strValue = strValues.get(i);
    
                if (strValue.length() > field.getLength()) {
                    strValue = strValue.substring(0, field.getLength());
                }
                
            }
        }

        return strValues;
    }

}
