package com.danieljoanol.pgsqlpopulator.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.danieljoanol.pgsqlpopulator.model.GenericType;

import net.datafaker.Faker;

public class Dates {
    
    private Dates() {}

    public static List<String> generateDates(Faker faker, String type, GenericType field, Integer recordsNumber) {
        
        List<Timestamp> timeValues = faker.collection(
                    () -> faker.date().between(
                            Timestamp.valueOf(field.getStartDate()), 
                            Timestamp.valueOf(field.getEndDate())))
                .len(recordsNumber)
                .generate();

        if (type.equals("DATE")) {
            return timeValues.stream().map(Dates::createDate).collect(Collectors.toList());
        }

        if (type.equals("TIME")) {
            return timeValues.stream().map(Dates::createTime).collect(Collectors.toList());
        }

        return timeValues.stream().map(Dates::createTimestamp).collect(Collectors.toList());
    }

    public static String createDate(Timestamp ts) {

        LocalDateTime dateTime = ts.toLocalDateTime();

        Integer month = dateTime.getMonthValue();
        Integer day = dateTime.getDayOfMonth();

        String date =
            dateTime.getYear() + "-";

        if (month < 10) {
            date += "0" + month.toString() + "-";
        } else {
            date += month.toString() + "-";
        }

        if (day < 10) {
            date += "0" + day.toString();
        } else {
            date += day.toString();
        }

        return date;
    }

    public static String createTime(Timestamp ts) {

        LocalDateTime dateTime = ts.toLocalDateTime();

        Integer hour = dateTime.getHour();
        Integer minute = dateTime.getMinute();
        Integer second = dateTime.getSecond();
        String time = "";
        
        if (hour < 10) {
            time += "0" + hour.toString() + ":";
        } else {
            time += hour.toString() + ":";
        }

        if (minute < 10) {
            time += "0" + minute.toString() + ":";
        } else {
            time += minute.toString() + ":";
        }

        if (second < 10) {
            time += "0" + second.toString();
        } else {
            time += second.toString();
        }

        return time;
    }

    public static String createTimestamp(Timestamp ts) {
        return createDate(ts) + " " + createTime(ts);
    }
}
