package com.danieljoanol.pgsqlpopulator.util;

import java.util.List;
import java.util.stream.Collectors;

import net.datafaker.Faker;

public class Numbers {
    
    public static List<String> generateBigInts(Faker faker, Integer recordsNumber) {

        List<Long> longValues = faker.collection(
                    () -> faker.number().numberBetween(-9223372036854775808L, 9223372036854775807L))
                .len(recordsNumber)
                .generate();

        return longValues.stream().map(String::valueOf).collect(Collectors.toList());
    }

    public static List<String> generateIntegers(Faker faker, Integer recordsNumber) {

        List<Integer> intValues = faker.collection(
                    () -> faker.number().numberBetween(-2147483648, 2147483647))
                .len(recordsNumber)
                .generate();

        return intValues.stream().map(String::valueOf).collect(Collectors.toList());
    }

    public static List<String> generateSmallInts(Faker faker, Integer recordsNumber) {

        List<Integer> intValues = faker.collection(
                    () -> faker.number().numberBetween(-32768, 32767))
                .len(recordsNumber)
                .generate();

        return intValues.stream().map(String::valueOf).collect(Collectors.toList());
    }
}
