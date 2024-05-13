package com.example.futsalmanagement.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingPojo {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private double price;
}

