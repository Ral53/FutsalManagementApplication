package com.example.futsalmanagement.service.impl;

import com.example.futsalmanagement.entity.Ground;
import com.example.futsalmanagement.pojo.GroundPojo;
import com.example.futsalmanagement.repository.GroundRepository;
import com.example.futsalmanagement.service.GroundService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroundServiceImpl implements GroundService {

    private final GroundRepository groundRepository;

    @Autowired
    public GroundServiceImpl(GroundRepository groundRepository) {
        this.groundRepository = groundRepository;
    }

    @Override
    public List<Ground> getAllGrounds() {
        return groundRepository.findAll();
    }

    @Override
    public Optional<Ground> getGroundById(Integer id) {
        return groundRepository.findById(id);
    }

    @Override
    public GroundPojo createGround(GroundPojo groundPojo) {
        Ground ground = convertToEntity(groundPojo);
        Ground savedGround = groundRepository.save(ground);
        return convertToPojo(savedGround);
    }

    @Override
    public boolean updateGround(Integer id, GroundPojo groundPojo) {
        Optional<Ground> optionalGround = groundRepository.findById(id);
        if (optionalGround.isPresent()) {
            Ground existingGround = optionalGround.get();
            BeanUtils.copyProperties(groundPojo, existingGround);
            groundRepository.save(existingGround);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGroundById(Integer id) {
        if (groundRepository.existsById(id)) {
            groundRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper method to convert between Entity and Pojo
    private Ground convertToEntity(GroundPojo groundPojo) {
        Ground ground = new Ground();
        BeanUtils.copyProperties(groundPojo, ground);
        return ground;
    }

    private GroundPojo convertToPojo(Ground ground) {
        GroundPojo groundPojo = new GroundPojo();
        BeanUtils.copyProperties(ground, groundPojo);
        return groundPojo;
    }
}
