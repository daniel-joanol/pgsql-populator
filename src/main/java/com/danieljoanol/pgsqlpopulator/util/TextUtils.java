package com.danieljoanol.pgsqlpopulator.util;

import java.util.List;

import net.datafaker.Faker;

public class TextUtils {

    private TextUtils() {}

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
    
}
