package com.walking.habits.petwalking.service;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class TimeClassification {
    private final LocalTime morningEndsAt = LocalTime.of(12, 0, 0);
    private final LocalTime noonEndsAt = LocalTime.of(19, 0, 0);
    private final LocalTime nightEndsAt = LocalTime.of(23, 59, 59);
    @Getter
    private final List<Duration> morningDurations = new ArrayList<>();
    @Getter
    private final List<Duration> noonDurations = new ArrayList<>();
    @Getter
    private final List<Duration> nightDurations = new ArrayList<>();
    @Getter
    private final HashMap<LocalDate, List<Duration>> dailyDurations = new HashMap<>();

    public void classifyTimeDurations(LocalDateTime start, LocalDateTime end) {

        Duration timeDifference = Duration.between(start, end);

        if (start.toLocalTime().isBefore(morningEndsAt))
            morningDurations.add(timeDifference);
        else if (start.toLocalTime().isAfter(morningEndsAt) && start.toLocalTime().isBefore(noonEndsAt))
            noonDurations.add(timeDifference);
        else if (start.toLocalTime().isAfter(noonEndsAt) && start.toLocalTime().isBefore(nightEndsAt))
            nightDurations.add(timeDifference);

        if (!dailyDurations.containsKey(start.toLocalDate()))
            dailyDurations.put(start.toLocalDate(), new ArrayList<>());

        dailyDurations.get(start.toLocalDate()).add(timeDifference);
    }


    public Duration averageDuration(List<Duration> durations) {

        long totalSecondsDuration = durations.stream().map(d -> d.getSeconds())
                .reduce((ds, acc) -> acc + ds).get();

        long average = Math.abs(totalSecondsDuration / durations.size());

        Duration totalDuration = Duration.ofSeconds(average);

        return totalDuration;
    }

    private long getTotalTimeInDurations(List<Duration> durations){
        return durations.stream()
                .map(d->d.getSeconds())
                .reduce((dS, acc)->acc+dS)
                .get();
    }
    public Duration getAverageDailyDuration() {

        long totalSecondsFromDays = 0;
        for (LocalDate timeKey : dailyDurations.keySet()) {
            long averageDurationOfDay = getTotalTimeInDurations(dailyDurations.get(timeKey));
            totalSecondsFromDays += averageDurationOfDay;
        }

        totalSecondsFromDays = totalSecondsFromDays / dailyDurations.size();

        Duration averageDurationsFromDays = Duration.ofSeconds(totalSecondsFromDays);

        return averageDurationsFromDays;
    }

}
