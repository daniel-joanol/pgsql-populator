package com.danieljoanol.pgsqlpopulator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.text.Char;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;

@Service
public class TextTypeServiceImpl implements TextTypesService {

    private final EasyRandom generator = new EasyRandom();

    @Override
    public Query addCharValue(Char field, Query queryObj) {

        final String typeName = "Char";
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

        String query = queryObj.getQuery();
        query += "'" + value.toString() + "', ";
        queryObj.setQuery(query);

        return queryObj;
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
