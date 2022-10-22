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
    private String[] items;
    private Integer length;

    // CHAR, SMALLINT, INTEGER and BIG_INT
    public GenericType(String name, FieldType type) {
        this.name = name;
        this.type = type;
    }

    // VARCHAR and TEXT
    public GenericType(String name, FieldType type, VarcharType varcharType,
            Integer length) {
        this.name = name;
        this.type = type;
        this.varcharType = varcharType;
        this.length = length;
    }

    // ENUM
    public GenericType(String name, String[] items) {
        this.name = name;
        this.items = items;
    }
    
}
