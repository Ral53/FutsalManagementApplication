package com.example.futsalmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Booking")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq_gen")
    @SequenceGenerator(name = "booking_seq_gen", sequenceName = "booking_-seq", allocationSize = 1)
    @Id
    private Integer id;

    @Column(name = "start-time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end-time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "Price", nullable = false)
    private double price;

}
