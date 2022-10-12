package com.danieljoanol.pgsqlpopulator.service;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;

public interface TextTypesService {

    public Query addCharValue(GenericType field, Query queryObj);

    public Query addEnumValue(GenericType field, Query queryObj);

    public Query addLongTextValue(GenericType field, Query queryObj);

    public Query addMediumTextValue(GenericType field, Query queryObj);

    public Query addSetValue(GenericType field, Query queryObj);

    public Query addTextValue(GenericType field, Query queryObj);

    public Query addTinyTextValue(GenericType field, Query queryObj);

    public Query addVarcharValue(GenericType field, Query queryObj);
    
}
