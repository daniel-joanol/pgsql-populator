package com.danieljoanol.pgsqlpopulator.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Dates {
    
    private Dates() {}

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
