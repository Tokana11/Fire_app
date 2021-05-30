package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.REST.RepairsRepository;
import com.fire_app.fire_app.REST.VehicleRepository;
import com.fire_app.fire_app.domain.model.Repair;
import com.fire_app.fire_app.domain.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

//    @RequestMapping(value = "/{regNumber}", method = RequestMethod.GET)
//    public Optional<List<Repair>> getRepairsHistory(@PathVariable(name = "regNumber") String regNumber) {
//        try {
//            Vehicle vehicle = vehicleRepository.findByRegNumber(regNumber);
//            if(vehicle != null){
//                List<Repair> repairs = repairsRepository.findAll();
//
//            }
//        }catch (Exception e){
//            return null;
//        }
//    }
}
