package com.danieljoanol.pgsqlpopulator.util;

import java.util.ArrayList;
import java.util.List;

import net.datafaker.Faker;

public class BooleanUtils {
    
    private BooleanUtils() {}

    public static List<String> createBooleans(Faker faker, Integer recordsNumber) {

        List<String> strValues = new ArrayList<>();
        List<Integer> intValues = faker.collection(
                    () -> faker.number().numberBetween(0, 2))
                .len(recordsNumber)
                .generate();

        for (Integer n : intValues) {
            if (n == 1) {
                strValues.add("false");
            } else {
                strValues.add("true");
            }
        }

        return strValues;
    }
}
