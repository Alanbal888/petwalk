package com.walking.habits.petwalking.util;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;

public class DateUtilTest {

    @Test
    public void getTimestampFromLocalDateTimeTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,9,23,12,0,0);

        Timestamp timestamp = DateUtil.getTimestampFromLocalDateTime(localDateTime);

        Assertions.assertNotNull(timestamp);

    }
}
