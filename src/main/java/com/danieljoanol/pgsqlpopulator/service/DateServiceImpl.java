package com.danieljoanol.pgsqlpopulator.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.danieljoanol.pgsqlpopulator.model.DateType;
import com.danieljoanol.pgsqlpopulator.util.DateUtils;

import net.datafaker.Faker;

@Service
public class DateServiceImpl implements DateService {

    Faker faker = new Faker(new Locale("en-US"));

    @Override
    public List<String> generateValues(Integer recordsNumber, DateType date) {
        
        List<Timestamp> timeValues = faker.collection(
                    () -> faker.date().between(
                            Timestamp.valueOf(date.getStartDate()), 
                            Timestamp.valueOf(date.getEndDate())))
                .len(recordsNumber)
                .generate();

        if (date.getType().equals("DATE")) {
            return timeValues.stream().map(DateUtils::createDate).collect(Collectors.toList());
        }

        if (date.getType().equals("TIME")) {
            return timeValues.stream().map(DateUtils::createTime).collect(Collectors.toList());
        }

        return timeValues.stream().map(DateUtils::createTimestamp).collect(Collectors.toList());
    }

}
