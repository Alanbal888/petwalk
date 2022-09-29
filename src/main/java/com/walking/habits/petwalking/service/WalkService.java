package com.walking.habits.petwalking.service;

import com.walking.habits.petwalking.controller.model.Walk;
import com.walking.habits.petwalking.persistence.entity.WalkInformation;
import com.walking.habits.petwalking.persistence.repository.WalkInformationRepository;
import com.walking.habits.petwalking.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WalkService {

    @Autowired
    private WalkInformationRepository walkInformationRepository;

    public void addNewWalkInformation(Walk walk){

        Timestamp startTime = DateUtil.getTimestampFromLocalDateTime(walk.getStarted());
        Timestamp endTime = DateUtil.getTimestampFromLocalDateTime(walk.getEnded());

        WalkInformation walkInformation = new WalkInformation();
        walkInformation.setTimeEnded(endTime);
        walkInformation.setTimeStarted(startTime);
        WalkInformation result=walkInformationRepository.save(walkInformation);
        log.info("Stored information successfully");
    }

    public List<Walk> getAllWalkInformation(){
        List<WalkInformation> walkInformations = walkInformationRepository.findAllByOrderByTimeStarted();
        return walkInformations.stream().map(this::mapWalkInformationToWalk).collect(Collectors.toList());
    }

    public Walk mapWalkInformationToWalk(WalkInformation walkInformation){
        return Walk.builder()
                .id(walkInformation.getWalkInformationId())
                .started(walkInformation.getTimeStarted().toLocalDateTime())
                .ended(walkInformation.getTimeEnded().toLocalDateTime())
                .build();
    }

    public void deleteWalk(Long id) throws Exception {
        Optional<WalkInformation> walkInformation = walkInformationRepository.findById(id);

        if(walkInformation.isPresent())
            walkInformationRepository.delete(walkInformation.get());
        else throw new Exception("No record found to delete");
    }
}
