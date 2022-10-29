package com.danieljoanol.pgsqlpopulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Model for fieldTypes CHAR, BOOLEAN, UUID, SMALLINT, INTEGER, BIG_INT and MONEY
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericType {

    private String name;
    private String type;
    
}
