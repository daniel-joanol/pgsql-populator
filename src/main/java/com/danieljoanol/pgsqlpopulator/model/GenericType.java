package com.danieljoanol.pgsqlpopulator.model;

import java.time.LocalDateTime;

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
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // CHAR, BOOLEAN, UUID, SMALLINT, INTEGER, BIG_INT and MONEY
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
    public GenericType(String name, FieldType type, String[] items) {
        this.name = name;
        this.type = type;
        this.items = items;
    }

    // DATE, TIME and TIMESTAMP
    public GenericType(String name, FieldType type, LocalDateTime startDate, 
            LocalDateTime endDate) {
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
}
