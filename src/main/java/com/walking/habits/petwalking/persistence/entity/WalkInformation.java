package com.walking.habits.petwalking.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="WALK_INFORMATION")
@Getter
@Setter
public class WalkInformation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="WALK_INFORMATION_ID")
    private Long walkInformationId;

    @Column(name="TIME_STARTED")
    private Timestamp timeStarted;

    @Column(name="TIME_ENDED")
    private Timestamp timeEnded;

    @Column(name="IS_TEST_DATA")
    private Boolean isTestData;

}
