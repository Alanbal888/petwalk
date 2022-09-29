package com.walking.habits.petwalking.service;

import com.walking.habits.petwalking.controller.model.Statistics;
import com.walking.habits.petwalking.controller.model.Walk;
import com.walking.habits.petwalking.controller.model.WalkInformationQueryHeaders;
import com.walking.habits.petwalking.persistence.entity.WalkInformation;
import com.walking.habits.petwalking.persistence.repository.WalkInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WalkStatisticsService {

    @Autowired
    private EntityManager entityManager;

    private WalkInformationStatisticCalculator walkInformationStatisticCalculator;

    public WalkStatisticsService() {
        walkInformationStatisticCalculator = new WalkInformationStatisticCalculator();
    }

    private String WALK_INFORMATION_TIME_STARTED_FIELD = "timeStarted";
    private String WALK_INFORMATION_TIME_ENDED_FIELD = "timeEnded";

    public Statistics getWalkStatistics(WalkInformationQueryHeaders walkInformationQueryHeaders) {

        List<WalkInformation> walkInformationList = getWalkInformation(walkInformationQueryHeaders);

        Statistics statistics = walkInformationStatisticCalculator.generateStatistics(walkInformationList);

        return statistics;
    }

    private List<WalkInformation> getWalkInformation(WalkInformationQueryHeaders walkInformationQueryHeaders) {
        CriteriaBuilder walkInformationCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WalkInformation> walkInformationCriteriaQuery = walkInformationCriteriaBuilder.createQuery(WalkInformation.class);
        Root<WalkInformation> walkInformationRoot = walkInformationCriteriaQuery.from(WalkInformation.class);

        List<Predicate> dateFilters =
                getWalkStatisticsPredicateForDates(walkInformationQueryHeaders.getStart(),
                        walkInformationQueryHeaders.getEnd(),
                        walkInformationRoot,
                        walkInformationCriteriaBuilder);

        walkInformationCriteriaQuery.where(dateFilters.toArray(new Predicate[dateFilters.size()]));

        return entityManager.createQuery(walkInformationCriteriaQuery)
                .getResultList();
    }

    private List<Predicate> getWalkStatisticsPredicateForDates(LocalDateTime startDate, LocalDateTime endDate,
                                                               Root root, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null) {
            Predicate greaterThanPredicate =
                    criteriaBuilder.greaterThan(root.get(WALK_INFORMATION_TIME_STARTED_FIELD), Timestamp.valueOf(startDate));
            predicates.add(greaterThanPredicate);
        }

        if (endDate != null) {
            Predicate lessThanPredicate =
                    criteriaBuilder.lessThan(root.get(WALK_INFORMATION_TIME_STARTED_FIELD), Timestamp.valueOf(endDate));
            predicates.add(lessThanPredicate);
        }

        return predicates;
    }

}
