package com.danieljoanol.pgsqlpopulator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Model used only by fieldType ENUM
 */
@Getter
@NoArgsConstructor
public class EnumType extends GenericType {

    private String[] items;

    public EnumType(String name, String type, String[] items) {
        super(name, type);
        this.items = items;
    }
    
}
