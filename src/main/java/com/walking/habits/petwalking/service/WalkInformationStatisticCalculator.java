package com.walking.habits.petwalking.service;

import com.walking.habits.petwalking.controller.model.Statistics;
import com.walking.habits.petwalking.persistence.entity.WalkInformation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public class WalkInformationStatisticCalculator {

    private TimeClassification timeClassification;

    public WalkInformationStatisticCalculator(){
        timeClassification = new TimeClassification();
    }

    public Statistics generateStatistics(List<WalkInformation> walkInformationList) {

        Statistics statistics = new Statistics();

        walkInformationList.stream()
                .forEach(walkInformation -> timeClassification.classifyTimeDurations(
                        walkInformation.getTimeStarted().toLocalDateTime(),
                        walkInformation.getTimeEnded().toLocalDateTime()));

        Duration averageMorningWalkDuration = timeClassification.averageDuration(timeClassification.getMorningDurations());
        Duration averageNoonWalkDuration = timeClassification.averageDuration(timeClassification.getNoonDurations());
        Duration averageNightWalkDuration = timeClassification.averageDuration(timeClassification.getNightDurations());
        Duration averageDailyWalkDuration = timeClassification.getAverageDailyDuration();



        statistics.setAverageWalkingTimeMorning(averageMorningWalkDuration);
        statistics.setAverageWalkingTimeNoon(averageNoonWalkDuration);
        statistics.setAverageWalkingTimeNight(averageNightWalkDuration);

        statistics.setAverageWalkingTimeDaily(averageDailyWalkDuration);

        return statistics;
    }



}
