package com.walking.habits.petwalking.persistence.repository;

import com.walking.habits.petwalking.persistence.entity.WalkInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalkInformationRepository extends JpaRepository<WalkInformation, Long> {

    List<WalkInformation> findAllByOrderByTimeStarted();
}
