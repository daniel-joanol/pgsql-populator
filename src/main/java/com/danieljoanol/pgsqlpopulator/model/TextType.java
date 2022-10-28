package com.danieljoanol.pgsqlpopulator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

/* 
 * Model for fieldTypes TEXT and VARCHAR 
 */
@Getter
@NoArgsConstructor
public class TextType extends GenericType {

    private String varcharType;
    private Integer length;
    
    public TextType(String name, String type, String varcharType, Integer length) {
        super(name, type);
        this.varcharType = varcharType;
        this.length = length;
    }
}
