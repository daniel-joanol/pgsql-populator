package com.danieljoanol.pgsqlpopulator.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Model for fieldTypes DATE, TIME and TIMESTAMP
 */
@Getter
@NoArgsConstructor
public class DateType extends GenericType {
    
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDate;

    public DateType(String name, String type, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
