package com.danieljoanol.pgsqlpopulator.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Model for fieldTypes DATE, TIME and TIMESTAMP
 */
@Getter
@NoArgsConstructor
public class DateType extends GenericType {
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public DateType(String name, String type, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
