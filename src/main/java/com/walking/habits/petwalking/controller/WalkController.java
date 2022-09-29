package com.walking.habits.petwalking.controller;

import com.walking.habits.petwalking.controller.model.Statistics;
import com.walking.habits.petwalking.controller.model.Walk;
import com.walking.habits.petwalking.controller.model.WalkInformationQueryHeaders;
import com.walking.habits.petwalking.service.WalkService;
import com.walking.habits.petwalking.service.WalkStatisticsService;
import com.walking.habits.petwalking.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/petwalk")
public class WalkController {

    @Autowired
    WalkService walkService;

    @Autowired
    WalkStatisticsService walkStatisticsService;

    @PostMapping
    public ResponseEntity addNewWalk(@RequestBody Walk walk){

        walkService.addNewWalkInformation(walk);

        return ResponseEntity.ok().body("All good");
    }

    @GetMapping
    public ResponseEntity<List<Walk>> getWalkInformation(){

        List<Walk> response = walkService.getAllWalkInformation();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWalk(@PathVariable("id") Long id) throws Exception {

        walkService.deleteWalk(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity getStatistics(@RequestParam(value = "startDate", required = false) String startDate, @RequestParam(value = "endDate", required = false)String endDate){


        WalkInformationQueryHeaders walkInformationQueryHeaders = WalkInformationQueryHeaders.builder()
                .start(DateUtil.getLocalDateTimeFromString(startDate))
                .end(DateUtil.getLocalDateTimeFromString(endDate))
                .build();
        Statistics statistics = walkStatisticsService.getWalkStatistics(walkInformationQueryHeaders);

        return ResponseEntity.ok(statistics);
    }
}
