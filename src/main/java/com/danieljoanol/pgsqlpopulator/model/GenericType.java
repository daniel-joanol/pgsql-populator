package com.danieljoanol.pgsqlpopulator.model;

import com.danieljoanol.pgsqlpopulator.model.enumarator.FieldType;

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
    
}
