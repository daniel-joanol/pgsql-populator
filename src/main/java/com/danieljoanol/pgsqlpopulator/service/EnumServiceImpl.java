package com.danieljoanol.pgsqlpopulator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.EnumType;
import com.danieljoanol.pgsqlpopulator.util.EnumUtils;

@Service
public class EnumServiceImpl implements EnumService {

    @Override
    public List<String> generateValues(Integer recordsNumber, EnumType enumt) {

        return EnumUtils.generateEnums(recordsNumber, enumt.getItems());
    }
}
