package com.salus.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public ZonedDateTime stringToDate(String dateString) {

        ZonedDateTime dateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);

        return dateTime;
    }

    public String dateToString(ZonedDateTime date){

        String dateTime = date.toString();

        return dateTime;
    }
}
