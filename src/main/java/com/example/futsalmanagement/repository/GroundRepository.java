package com.example.futsalmanagement.repository;

import com.example.futsalmanagement.entity.Ground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroundRepository extends JpaRepository<Ground, Integer> {
}
