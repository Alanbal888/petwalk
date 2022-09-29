package com.walking.habits.petwalking.controller.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class WalkInformationQueryHeaders {
    LocalDateTime start;
    LocalDateTime end;
}
