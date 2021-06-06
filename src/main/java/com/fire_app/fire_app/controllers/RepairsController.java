package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.REST.RepairsRepository;
import com.fire_app.fire_app.REST.VehicleRepository;
import com.fire_app.fire_app.domain.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/repairs", produces = "application/json")
public class RepairsController {

    private final RepairsRepository repairsRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public RepairsController(RepairsRepository repairsRepository, VehicleRepository vehicleRepository) {
        this.repairsRepository = repairsRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Repair>> findAll(){
        List<Repair> repairs = repairsRepository.findAll();
        return new ResponseEntity<>(repairs,HttpStatus.OK);
    }

    @GetMapping(value = "/{regNumber}")
    public ResponseEntity getRepairsHistory(@PathVariable(name = "regNumber") String regNumber) {
        try {
          Set<Repair> repairs = repairsRepository.findRepairsByVehicleRegNumber(regNumber);
            return new ResponseEntity<>(repairs, HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }
}
