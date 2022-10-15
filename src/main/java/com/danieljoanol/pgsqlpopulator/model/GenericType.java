package com.danieljoanol.pgsqlpopulator.model;

import java.util.ArrayList;
import java.util.List;

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
    private Boolean unique;
    private Integer minLength;
    private Integer maxLength;

    //Used only by types ENUM and SET
    List<String> items = new ArrayList<>();
    
}
