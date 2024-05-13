package com.example.futsalmanagement.service;

import com.example.futsalmanagement.entity.Ground;
import com.example.futsalmanagement.pojo.GroundPojo;

import java.util.List;
import java.util.Optional;

public interface GroundService {
    List<Ground> getAllGrounds();

    Optional<Ground> getGroundById(Integer id);

    GroundPojo createGround(GroundPojo groundPojo);

    boolean updateGround(Integer id, GroundPojo groundPojo);

    boolean deleteGroundById(Integer id);
}
