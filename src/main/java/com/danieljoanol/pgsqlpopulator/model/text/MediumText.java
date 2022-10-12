package com.danieljoanol.pgsqlpopulator.model.text;

import com.danieljoanol.pgsqlpopulator.model.GenericType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediumText extends GenericType {
    
    private Integer length;
}
