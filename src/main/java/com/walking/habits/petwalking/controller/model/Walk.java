package com.walking.habits.petwalking.controller.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Walk {
    private Long id;
    private LocalDateTime started;
    private LocalDateTime ended;
}
