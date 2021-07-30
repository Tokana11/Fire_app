package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.domain.model.Repair;
import com.fire_app.fire_app.service.RepairsService;
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
import java.util.Set;

@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/repair")
public class RepairsController {

    private final RepairsService repairsService;

    @Autowired
    public RepairsController(RepairsService repairsService) {
        this.repairsService = repairsService;
    }

    @GetMapping
    public ResponseEntity<List<Repair>> findAll() {
        return new ResponseEntity<>(repairsService.getAllRepairs(), HttpStatus.OK);
    }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        Optional<Repair> repair = repairsService.getRepairById(id);
        if (repair.isPresent()) {
            return new ResponseEntity<>(repair.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorMessage("Repair with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/byVehicleId/{id}")
    public ResponseEntity<?> getRepairsByVehicleId(@PathVariable(name = "id") Long id) {
        Optional<Set<Repair>> repairs = Optional.ofNullable(repairsService.getRepairsByVehicleId(id));
        if (repairs.isPresent()) {
            return new ResponseEntity<>(repairs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No vehicle with id: " + id + " found!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/byVehicleRegNumber/{regNumber}")
    public ResponseEntity<?> getRepairsByRegNumber(@PathVariable(name = "regNumber") String regNumber) {
        Optional<Set<Repair>> repairs = Optional.ofNullable(repairsService.getRepairByVehicleRegNumber(regNumber));
        if (repairs.isPresent()) {
            return new ResponseEntity<>(repairs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No vehicle with register number: " + regNumber + " found!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createRepairRecord(@RequestBody Repair repair) {
        Optional<ErrorMessage> optionalErrorMessage = repairsService.createRepairRecord(repair);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(repair.getId(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRepairRecord(@RequestBody Repair repair, @PathVariable Long id) {
        Optional<ErrorMessage> optionalErrorMessage = repairsService.updateRepairRecord(repair, id);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRepairRecord(@PathVariable Long id) {
        Optional<ErrorMessage> optionalErrorMessage = repairsService.deleteRepairRecord(id);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
