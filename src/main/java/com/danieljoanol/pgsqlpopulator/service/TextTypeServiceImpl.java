package com.danieljoanol.pgsqlpopulator.service;

import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;

@Service
public class TextTypeServiceImpl implements TextTypesService {

    @Override
    public Query addCharValue(GenericType field, Query queryObj) {

        final EasyRandom generator = new EasyRandom();
        final String typeName = field.getName();
        Character value = generator.nextObject(Character.class);
        boolean isPresent = true;

        if (field.getUnique()) {

            if (queryObj.getUniqueValues().get(typeName) != null) {

                do {

                    if (queryObj.getUniqueValues().get(typeName).contains(value.toString())) {
                        value = generator.nextObject(Character.class);
                    } else {
                        isPresent = false;
                    }

                } while (isPresent);

                queryObj.getUniqueValues().get(typeName).add(value.toString());

            } else {

                List<String> values = new ArrayList<>();
                values.add(value.toString());
                queryObj.getUniqueValues().put(typeName, values);
            }

        }

        return addFinalValue(value.toString(), queryObj);
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
        
        if (field.getMinLength() == null || field.getMinLength() < 0 ||
                field.getMaxLength() == null || field.getMaxLength() > 65535) {

            throw new InvalidParameterException("Length has to be between 0 and 65535");
        }
        
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.stringLengthRange(field.getMaxLength(), field.getMaxLength());
        final EasyRandom generator = new EasyRandom(parameters);
        
        final String typeName = field.getName();
        String value = generator.nextObject(String.class);
        boolean isPresent = true;

        if (field.getUnique()) {

            if (queryObj.getUniqueValues().get(typeName) != null) {

                do {

                    if (queryObj.getUniqueValues().get(typeName).contains(value.toString())) {
                        value = generator.nextObject(String.class);
                    } else {
                        isPresent = false;
                    }

                } while (isPresent);

                queryObj.getUniqueValues().get(typeName).add(value.toString());

            } else {

                List<String> values = new ArrayList<>();
                values.add(value.toString());
                queryObj.getUniqueValues().put(typeName, values);
            }

        }

        return addFinalValue(value.toString(), queryObj);
    }

    public Query addFinalValue(String value, Query queryObj) {
        
        String query = queryObj.getQuery();
        query += "'" + value + "', ";
        queryObj.setQuery(query);

        return queryObj;
    }

}
