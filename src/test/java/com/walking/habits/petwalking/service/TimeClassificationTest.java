package com.walking.habits.petwalking.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TimeClassificationTest {

    private TimeClassification timeClassification = new TimeClassification();

    List<TimeData> sampleTestData = generateSampleData();

    @Test
    public void validateTimeClassification_validateFinalLengthOfCollectionItems() {

        sampleTestData.stream().forEach(timeData -> {
            timeClassification.classifyTimeDurations(timeData.start, timeData.end);
        });

        assertEquals(2, timeClassification.getDailyDurations().size());
        assertEquals(2, timeClassification.getMorningDurations().size());
        assertEquals(2, timeClassification.getNoonDurations().size());
        assertEquals(2, timeClassification.getNightDurations().size());
    }

    @Test
    public void averageDuration_test(){
        sampleTestData.stream().forEach(timeData -> {
            timeClassification.classifyTimeDurations(timeData.start, timeData.end);
        });

        List<Duration> morningDurations = timeClassification.getMorningDurations();

        Duration duration = timeClassification.averageDuration(morningDurations);

        assertEquals(15, duration.toMinutes());
    }

    @Test
    public void averageDailyDurationTest(){
        sampleTestData.stream().forEach(timeData -> {
            timeClassification.classifyTimeDurations(timeData.start, timeData.end);
        });

        Duration averageDailyDuration = timeClassification.getAverageDailyDuration();

        assertEquals(45,averageDailyDuration.toMinutes());
    }

    private List<TimeData> generateSampleData() {
        List<TimeData> data = new ArrayList<>();
        //Day 1
        TimeData timeData1 = new TimeData(LocalDateTime.of(2022, 9, 26, 6, 15, 0),
                LocalDateTime.of(2022, 9, 26, 6, 30, 0));

        TimeData timeData2 = new TimeData(LocalDateTime.of(2022, 9, 26, 13, 15, 0),
                LocalDateTime.of(2022, 9, 26, 13, 30, 0));

        TimeData timeData3 = new TimeData(LocalDateTime.of(2022, 9, 26, 21, 15, 0),
                LocalDateTime.of(2022, 9, 26, 21, 30, 0));

        //Day 2
        TimeData timeData4 = new TimeData(LocalDateTime.of(2022, 9, 27, 6, 15, 0),
                LocalDateTime.of(2022, 9, 27, 6, 30, 0));

        TimeData timeData5 = new TimeData(LocalDateTime.of(2022, 9, 27, 13, 15, 0),
                LocalDateTime.of(2022, 9, 27, 13, 30, 0));

        TimeData timeData6 = new TimeData(LocalDateTime.of(2022, 9, 27, 21, 15, 0),
                LocalDateTime.of(2022, 9, 27, 21, 30, 0));

        data.add(timeData1);
        data.add(timeData2);
        data.add(timeData3);
        data.add(timeData4);
        data.add(timeData5);
        data.add(timeData6);
        return data;
    }
}

class TimeData {
    LocalDateTime start;
    LocalDateTime end;

    TimeData(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
