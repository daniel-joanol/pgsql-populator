package com.danieljoanol.pgsqlpopulator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.datafaker.Faker;

public class Varchars {

    private Varchars() {}

    public static List<String> generateFirstNames(Faker faker, Integer recordsNumber) {
        
        return faker.collection(
                    () -> faker.name().firstName())
                .len(recordsNumber)
                .generate();
    }

    public static List<String> generateLastNames(Faker faker, Integer recordsNumber) {

        return faker.collection(
                    () -> faker.name().lastName())
                .len(recordsNumber)
                .generate();
    }

    public static List<String> generateFullNames(Faker faker, Integer recordsNumber) {
        return faker.collection(
                    () -> faker.name().fullName())
                .len(recordsNumber)
                .generate();
    }

    public static List<String> generateAddresses(Faker faker, Integer recordsNumber) {
        return faker.collection(
                    () -> faker.address().fullAddress())
                .len(recordsNumber)
                .generate();
    }

    public static List<String> generateCompanies(Faker faker, Integer recordsNumber) {
        return faker.collection(
                    () -> faker.company().name())
                .len(recordsNumber)
                .generate();
    }

    public static List<String> generateIdNumbers(Faker faker, Integer recordsNumber) {
        return faker.collection(
                    () -> faker.idNumber().valid())
                .len(recordsNumber)
                .generate();
    }

    public static List<String> generatePhoneNumbers(Faker faker, Integer recordsNumber) {
        return faker.collection(
                    () -> faker.phoneNumber().cellPhone())
                .len(recordsNumber)
                .generate();
    }

    public static List<String> generateEnums(Faker faker, Integer recordsNumber, String[] items) {

        Random random = new Random();
        List<String> strValues = new ArrayList<>();
        String strValue;
        
        Integer n = random.nextInt(items.length);

        for (int i = 0; i < recordsNumber; i++) {
            strValue = items[n];
            strValues.add(strValue);
        }

        return strValues;
    }
    
}
