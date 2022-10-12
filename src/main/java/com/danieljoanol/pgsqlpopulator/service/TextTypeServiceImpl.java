package com.danieljoanol.pgsqlpopulator.service;

import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;

@Service
public class TextTypeServiceImpl implements TextTypesService {

    private final EasyRandom generator = new EasyRandom();

    @Override
    public Query addCharValue(GenericType field, Query queryObj) {
        


        return null;
    }

    @Override
    public Query addEnumValue(GenericType field, Query queryObj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Query addLongTextValue(GenericType field, Query queryObj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Query addMediumTextValue(GenericType field, Query queryObj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Query addSetValue(GenericType field, Query queryObj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Query addTextValue(GenericType field, Query queryObj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Query addTinyTextValue(GenericType field, Query queryObj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Query addVarcharValue(GenericType field, Query queryObj) {
        // TODO Auto-generated method stub
        return null;
    }

}
