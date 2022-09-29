package com.walking.habits.petwalking.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class Statistics {
    private Duration averageWalkingTimeDaily;
    private Duration averageWalkingTimeMorning;
    private Duration averageWalkingTimeNoon;
    private Duration averageWalkingTimeNight;
}
