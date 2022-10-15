package com.danieljoanol.pgsqlpopulator.service;

import com.danieljoanol.pgsqlpopulator.model.GenericType;
import com.danieljoanol.pgsqlpopulator.model.utils.Query;

public interface TextTypesService {

    public Query addValue(GenericType field, Query queryObj);

}
