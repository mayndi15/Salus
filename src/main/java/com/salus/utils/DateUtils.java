package com.salus.utils;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static ZonedDateTime convertStringToZonedDateTime(String stringDateTime) {
        return stringDateTime == null || stringDateTime.isEmpty() ? null : ZonedDateTime.parse(stringDateTime, DATE_FORMAT);
    }

    public static String convertZonedDateTimeToString(ZonedDateTime zonedDateTime){
        return zonedDateTime == null ? null : zonedDateTime.toString();
    }

    public static LocalDate convertStringToLocalDate(String stringDate) {
        return stringDate == null || stringDate.isEmpty() ? null : LocalDate.parse(stringDate, DATE_FORMAT);
    }

    public static String convertLocalDateToString(LocalDate localDate) {
        return localDate == null ? null : localDate.toString();
    }
}
