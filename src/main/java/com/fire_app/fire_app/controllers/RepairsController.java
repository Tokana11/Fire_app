package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.repository.RepairsRepository;
import com.fire_app.fire_app.repository.VehicleRepository;
import com.fire_app.fire_app.domain.model.Repair;
import com.fire_app.fire_app.domain.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/repair")
public class RepairsController {

    private final RepairsRepository repairsRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public RepairsController(RepairsRepository repairsRepository, VehicleRepository vehicleRepository) {
        this.repairsRepository = repairsRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Repair>> findAll() {
        List<Repair> repairs = repairsRepository.findAll();
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }

    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<Set<Repair>> getRepairsByVehicleId(@PathVariable(name = "id") Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            Set<Repair> repairs = repairsRepository.findRepairsByVehicleId(id);
            return new ResponseEntity<>(repairs, HttpStatus.OK);
        } else {
            return new ResponseEntity("No records for vehicle with id: " + id + " found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/vehicle/regNumber/{regNumber}")
    public ResponseEntity<Set<Repair>> getRepairsByRegNumber(@PathVariable(name = "regNumber") String regNumber) {
        if (regNumber.isBlank() && !Character.isLetter(regNumber.charAt(0))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Vehicle> vehicle = vehicleRepository.findOptionalByRegNumber(regNumber);
        if (vehicle.isPresent()) {
            Set<Repair> repairs = repairsRepository.findRepairsByVehicleRegNumber(regNumber);
            return new ResponseEntity<>(repairs, HttpStatus.OK);
        }
        return new ResponseEntity("No records for vehicle with register number: " +
                regNumber + " found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Repair> createNewRepairRecord(@RequestBody Repair repair) {
        repairsRepository.save(repair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repair> updateRepairRecord(@PathVariable(name = "id") Long id,
                                                     @RequestBody Repair repair) {
    return null;
    // TODO fix it

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRepairRecordById(@PathVariable(name = "id") Long id) {
        Optional<Repair> repairRecord = repairsRepository.findById(id);
        if (repairRecord.isPresent()) {
            repairsRepository.deleteById(id);
            return new ResponseEntity<>(repairRecord, HttpStatus.OK);
        }
        return new ResponseEntity<>("No repair record with id: " + id + " found!", HttpStatus.BAD_REQUEST);
    }

    // UPDATE -> PUT; CREATE -> POST;
}
