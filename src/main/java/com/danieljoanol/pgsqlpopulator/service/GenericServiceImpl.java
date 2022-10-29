package com.danieljoanol.pgsqlpopulator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.util.BooleanUtils;
import com.danieljoanol.pgsqlpopulator.util.CharUtils;
import com.danieljoanol.pgsqlpopulator.util.NumberUtils;
import com.danieljoanol.pgsqlpopulator.util.UUIDUtils;

import net.datafaker.Faker;

@Service
public class GenericServiceImpl implements GenericService {

    Faker faker = new Faker(new Locale("en-US"));

    @Override
    public List<String> generateValues(Integer recordsNumber, GenericType generic) {
        
        List<String> strValues = new ArrayList<>();

        switch (generic.getType()) {

            case "CHAR":
                strValues = CharUtils.generateChars(recordsNumber);
                break;

            case "SMALL_INT":
                strValues = NumberUtils.generateSmallInts(faker, recordsNumber);
                break;

            case "INTEGER":
                strValues = NumberUtils.generateIntegers(faker, recordsNumber);
                break;

            case "BIG_INT", "MONEY":
                strValues = NumberUtils.generateBigInts(faker, recordsNumber);
                break;

            case "BOOLEAN":
                strValues = BooleanUtils.createBooleans(faker, recordsNumber);
                break;

            case "UUID":
                strValues = UUIDUtils.createUUIDs(recordsNumber);
                break;

        }

        return strValues;
    }
    
}
