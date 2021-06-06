package com.fire_app.fire_app.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.REST.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        try {
            List<Vehicle> vehicles = vehicleRepository.findAll();
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable("id") Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/register-number")
    public ResponseEntity<Vehicle> findByRegNumber(@RequestParam("regNumber") String regNumber) {
        Optional<Vehicle> vehicle = Optional.ofNullable(vehicleRepository.findByRegNumber(regNumber));
        return vehicle.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Vehicle> createNewVehicle(@RequestBody Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findByRegNumber(vehicle.getRegNumber());
        if (existingVehicle != null) {
            return new ResponseEntity("Item already exists!", HttpStatus.CONFLICT);
        } else {
            final Vehicle newVehicle = new Vehicle();
            newVehicle.setRegNumber(vehicle.getRegNumber());
            newVehicle.setVinNumber(vehicle.getVinNumber());
            newVehicle.setBrand(vehicle.getBrand());
            newVehicle.setModelName(vehicle.getModelName());
            newVehicle.setEngineOil(vehicle.getEngineOil());
            newVehicle.setEngineOilVolume(vehicle.getEngineOilVolume());
            newVehicle.setHydraulicOil(vehicle.getHydraulicOil());
            newVehicle.setHydraulicOilVolume(vehicle.getHydraulicOilVolume());
            newVehicle.setPumpOil(vehicle.getPumpOil());
            newVehicle.setPumpOilVolume(vehicle.getPumpOilVolume());
            newVehicle.setDifferentialOil(vehicle.getDifferentialOil());
            newVehicle.setDifferentialOilVolume(vehicle.getDifferentialOilVolume());
            newVehicle.setGearBoxOil(vehicle.getGearBoxOil());
            newVehicle.setGearBoxOilVolume(vehicle.getGearBoxOilVolume());
            newVehicle.setBrakeFluid(vehicle.getBrakeFluid());
            newVehicle.setBrakeFluidVolume(vehicle.getBrakeFluidVolume());
            newVehicle.setAntiFreeze(vehicle.getAntiFreeze());
            newVehicle.setAntiFreezeVolume(vehicle.getAntiFreezeVolume());
            newVehicle.setFuel(vehicle.getFuel());
            newVehicle.setFuelTankVolume(vehicle.getFuelTankVolume());
            newVehicle.setWaterTankVolume(vehicle.getWaterTankVolume());
            newVehicle.setFoamTankVolume(vehicle.getFoamTankVolume());
            newVehicle.setTyre(vehicle.getTyre());
            vehicleRepository.saveAndFlush(newVehicle);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newVehicle);
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        try {
            vehicleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



