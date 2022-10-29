package com.danieljoanol.pgsqlpopulator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnumUtils {
    
    private EnumUtils() {}

    public static List<String> generateEnums(Integer recordsNumber, String[] items) {

        Random random = new Random();
        List<String> strValues = new ArrayList<>();
        String strValue;
        Integer n;

        for (int i = 0; i < recordsNumber; i++) {
            n = random.nextInt(items.length);
            strValue = items[n];
            strValues.add(strValue);
        }

        return strValues;
    }
}
