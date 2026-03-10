package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Reservation {

    private Integer id;
    private Integer peopleCount;
    private String representativeName;
    private String phoneNumber;
    private String courseName;
    private String remarks;
    private LocalDateTime createdAt;
    private Integer memberId;
    private String status;
    private LocalDateTime reservationDate;
}