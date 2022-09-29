package com.walking.habits.petwalking.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static Timestamp getTimestampFromLocalDateTime(LocalDateTime localDateTime){
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    public static LocalDateTime getLocalDateTimeFromString(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
        return date;
    }
}
