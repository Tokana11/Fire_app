package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.domain.model.VehicleSpecs;
import com.fire_app.fire_app.service.VehicleSpecsService;
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
@RequestMapping(value = "/specs")
public class VehicleSpecsController {

    private final VehicleSpecsService vehicleSpecsService;

    @Autowired
    public VehicleSpecsController(VehicleSpecsService vehicleSpecsService) {
        this.vehicleSpecsService = vehicleSpecsService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleSpecs>> getAllSpecRecords() {
        return new ResponseEntity<>(vehicleSpecsService.getAllVehiclesSpecs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleSpecsById(@PathVariable Long id) {
        Optional<VehicleSpecs> vehicleSpecs = vehicleSpecsService.getVehicleSpecsById(id);
        if (vehicleSpecs.isPresent()) {
            return new ResponseEntity<>(vehicleSpecs.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorMessage("Vehicle specs record with id: " + id + " not found!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byVehicleId/{id}")
    public ResponseEntity<?> getVehicleSpecByVehicleId(@PathVariable Long id) {
        Optional<VehicleSpecs> optionalVehicleSpecs = Optional.ofNullable(vehicleSpecsService.getVehicleSpecByVehicleId(id));
        if (optionalVehicleSpecs.isPresent()) {
            return new ResponseEntity<>(optionalVehicleSpecs.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorMessage("No vehicle specs record for vehicle with id: " + id + " found!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byVehicleRegNumber/{regNumber}")
    public ResponseEntity<?> getVehicleSpecsByVehicleRegNumber(@PathVariable String regNumber){
        Optional<VehicleSpecs> optionalVehicleSpecs = Optional.ofNullable(vehicleSpecsService.getVehicleSpecsByVehicleRegNumber(regNumber));
        if(optionalVehicleSpecs.isPresent()){
            return new ResponseEntity<>(optionalVehicleSpecs.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ErrorMessage("No vehicle specs for vehicle with register number: "
                    + regNumber + " found!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createVehicleSpecsRecord(@RequestBody VehicleSpecs vehicleSpecs){
        Optional<ErrorMessage> optionalErrorMessage = vehicleSpecsService.createVehicleSpecsRecord(vehicleSpecs);
        if(optionalErrorMessage.isPresent()){
            return new ResponseEntity<>(optionalErrorMessage.get(),HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateVehicleSpecsRecord(@RequestBody VehicleSpecs vehicleSpecs, @PathVariable Long id){
        Optional<ErrorMessage> optionalErrorMessage = vehicleSpecsService.updateVehicleSpecsRecord(vehicleSpecs, id);
        if(optionalErrorMessage.isPresent()){
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleSpecsRecord(@PathVariable Long id){
        Optional<ErrorMessage> optionalErrorMessage = vehicleSpecsService.deleteVehicleSpecsRecord(id);
        if(optionalErrorMessage.isPresent()){
            return new ResponseEntity<>(optionalErrorMessage.get(),HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
