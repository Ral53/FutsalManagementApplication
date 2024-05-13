package com.example.futsalmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grounds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int size; // Size in square meters

    @Column(nullable = false)
    private double price; // Price per hour or session

}
