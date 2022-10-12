package com.danieljoanol.pgsqlpopulator.model.text;

import java.util.ArrayList;
import java.util.List;

import com.danieljoanol.pgsqlpopulator.model.GenericType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Set extends GenericType {
    
    private List<String> items = new ArrayList<>();
}
