package com.danieljoanol.pgsqlpopulator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.TextType;
import com.danieljoanol.pgsqlpopulator.util.VarcharUtils;

import net.datafaker.Faker;

@Service
public class TextServiceImpl implements TextService {

    Faker faker = new Faker(new Locale("en-US"));
    
    @Override
    public List<String> generateValues(Integer recordsNumber, TextType text) {
        
        List<String> strValues = new ArrayList<>();

        switch (text.getVarcharType()) {

            case "FIRST_NAME":
                strValues = VarcharUtils.generateFirstNames(faker, recordsNumber);
                break;

            case "LAST_NAME":
                strValues = VarcharUtils.generateLastNames(faker, recordsNumber);
                break;

            case "FULL_NAME":
                strValues = VarcharUtils.generateFullNames(faker, recordsNumber);
                break;

            case "ADDRESS":
                strValues = VarcharUtils.generateAddresses(faker, recordsNumber);
                break;

            case "COMPANY":
                strValues = VarcharUtils.generateCompanies(faker, recordsNumber);
                break;

            case "ID_NUMBER":
                strValues = VarcharUtils.generateIdNumbers(faker, recordsNumber);
                break;

            case "PHONE_NUMBER":
                strValues = VarcharUtils.generatePhoneNumbers(faker, recordsNumber);
                break;
        }
        
        if (text.getLength() != null) {
            for (int i = 0; i < recordsNumber; i++) {
                String strValue = strValues.get(i);
    
                if (strValue.length() > text.getLength()) {
                    strValue = strValue.substring(0, text.getLength());
                    strValues.set(i, strValue);
                }
            }
    
        }
        
        return strValues;
    }
 
    
}
