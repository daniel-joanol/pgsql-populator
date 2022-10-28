package com.danieljoanol.pgsqlpopulator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chars {
    
    public static String[] array = new String[] {
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
        "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
        "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
        "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
        "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9"
    };

    private Chars() {}

    public static List<String> generateChars(Integer recordsNumber) {

        List<String> strValues = new ArrayList<>();

        if (recordsNumber < strValues.size()) {
            strValues = Arrays.asList(array);
        } else {
            List<String> valuesA = Arrays.asList(array);
            List<String> valuesB = Arrays.asList(array);
            strValues = Stream.concat(valuesA.stream(), valuesB.stream())
                    .collect(Collectors.toList());
        }

        return strValues;
    }
}
