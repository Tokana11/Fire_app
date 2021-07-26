package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.service.VehicleService;
import com.fire_app.fire_app.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorMessage("Vehicle with id: " + id + " not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/byRegNumber/{regNumber}")
    public ResponseEntity<?> findByRegNumber(@PathVariable("regNumber") String regNumber) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleByRegNumber(regNumber);
        if (vehicle.isPresent()) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorMessage("Vehicle with register number: " + regNumber + " not found !"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewVehicle(@RequestBody Vehicle vehicle) {
        Optional<ErrorMessage> optionalErrorMessage = vehicleService.createVehicle(vehicle);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(vehicle.getId(), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle, @PathVariable("id") Long id) {
        Optional<ErrorMessage> optionalErrorMessage = vehicleService.updateVehicle(vehicle, id);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Optional<ErrorMessage> optionalErrorMessage = vehicleService.deleteVehicle(id);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}



