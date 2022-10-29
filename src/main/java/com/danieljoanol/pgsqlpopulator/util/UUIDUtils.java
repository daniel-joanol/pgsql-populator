package com.danieljoanol.pgsqlpopulator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UUIDUtils {
    
    private UUIDUtils() {}

    public static List<String> createUUIDs(Integer recordsNumber) {
        
        List<String> strValues = new ArrayList<>();
        String strValue;
        boolean newValue = false;

        for (int i = 0; i < recordsNumber; i++) {
            do {
                    
                strValue = UUID.randomUUID().toString();

                if (!strValues.contains(strValue)) {
                    newValue = true;
                    strValues.add(strValue);
                }

            } while (!newValue);
        }

        return strValues;
    }
}
