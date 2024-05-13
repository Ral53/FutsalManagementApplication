package com.example.futsalmanagement.controller;

import com.example.futsalmanagement.entity.Ground;
import com.example.futsalmanagement.pojo.GroundPojo;
import com.example.futsalmanagement.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grounds")
public class GroundController {

    private final GroundService groundService;

    @Autowired
    public GroundController(GroundService groundService) {
        this.groundService = groundService;
    }

    @GetMapping
    public ResponseEntity<List<GroundPojo>> getAllGrounds() {
        List<Ground> grounds = groundService.getAllGrounds();
        List<GroundPojo> groundPojos = grounds.stream()
                .map(this::convertToPojo)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(groundPojos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroundPojo> getGroundById(@PathVariable Integer id) {
        Optional<Ground> groundOptional = groundService.getGroundById(id);
        return groundOptional.map(ground -> ResponseEntity.ok().body(convertToPojo(ground)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<GroundPojo> createGround(@RequestBody GroundPojo groundPojo) {
        GroundPojo createdGround = groundService.createGround(groundPojo);
        return new ResponseEntity<>(createdGround, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateGround(@PathVariable Integer id, @RequestBody GroundPojo groundPojo) {
        boolean updated = groundService.updateGround(id, groundPojo);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGroundById(@PathVariable Integer id) {
        boolean deleted = groundService.deleteGroundById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Helper method to convert Ground entity to GroundPojo
    private GroundPojo convertToPojo(Ground ground) {
        GroundPojo groundPojo = new GroundPojo();
        groundPojo.setId(ground.getId());
        groundPojo.setName(ground.getName());
        groundPojo.setLocation(ground.getLocation());
        groundPojo.setSize(ground.getSize());
        groundPojo.setPrice(ground.getPrice());
        return groundPojo;
    }
}
