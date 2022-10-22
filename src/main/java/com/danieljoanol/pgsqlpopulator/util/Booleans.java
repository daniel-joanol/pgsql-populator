package com.danieljoanol.pgsqlpopulator.util;

public class Booleans {
    
    private Booleans() {}

    public static String createBoolean(Integer n) {
        
        if (n == 1) {
            return "false";
        } else {
            return "true";
        }
    }
}
