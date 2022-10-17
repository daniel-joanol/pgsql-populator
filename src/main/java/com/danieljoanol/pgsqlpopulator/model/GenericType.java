package com.danieljoanol.pgsqlpopulator.model;

import com.danieljoanol.pgsqlpopulator.model.enumarator.FieldType;
import com.danieljoanol.pgsqlpopulator.model.enumarator.VarcharType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericType {

    private String name;
    private FieldType type;
    private VarcharType varcharType;
    private Boolean unique;
    private String[] items;
    private Integer length;

    // Char
    public GenericType(String name, FieldType type, Boolean unique) {
        this.name = name;
        this.type = type;
        this.unique = unique;
    }

    // Varchar and Text
    public GenericType(String name, FieldType type, Boolean unique, VarcharType varcharType,
            Integer length) {
        this.name = name;
        this.type = type;
        this.unique = unique;
        this.varcharType = varcharType;
        this.length = length;
    }

    // Enum
    public GenericType(String name, String[] items) {
        this.name = name;
        this.items = items;
    }
    
}
