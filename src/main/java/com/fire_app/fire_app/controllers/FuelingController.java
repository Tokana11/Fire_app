package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.domain.model.Fueling;
import com.fire_app.fire_app.service.FuelingService;
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
@RequestMapping(value = "/fueling")
public class FuelingController {
    private final FuelingService fuelingService;

    @Autowired
    public FuelingController(FuelingService fuelingService) {
        this.fuelingService = fuelingService;
    }

    @GetMapping
    public ResponseEntity<List<Fueling>> findAllFuelings() {
        return new ResponseEntity<>(fuelingService.getAllFuelings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findFuelingById(@PathVariable Long id) {
        Optional<Fueling> optionalFueling = fuelingService.getFuelingById(id);
        if (optionalFueling.isPresent()) {
            return new ResponseEntity<>(optionalFueling.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorMessage("Fueling with id:" + id + " not found!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byVehicleId/{id}")
    public ResponseEntity<?> findFuelingsByVehicleId(@PathVariable Long id) {
        Optional<Set<Fueling>> fuelings = Optional.ofNullable(fuelingService.getFuelingsByVehicleId(id));
        if (fuelings.isPresent()) {
            return new ResponseEntity<>(fuelings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No vehicle with id: " + id + " found!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byVehicleRegNumber/{regNumber}")
    public ResponseEntity<?> findFuelingsByVehicleRegNumber(@PathVariable String regNumber) {
        Optional<Set<Fueling>> fuelings = Optional.ofNullable(fuelingService.getFuelingsByVehicleRegNumber(regNumber));
        if (fuelings.isPresent()) {
            return new ResponseEntity<>(fuelings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No vehicle with register number: " + regNumber + " found!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createFuelingRecord(@RequestBody Fueling fueling) {
        Optional<ErrorMessage> optionalErrorMessage = fuelingService.createFuelingRecord(fueling);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(fueling.getId(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFuelingRecord(@RequestBody Fueling fueling, @PathVariable Long id) {
        Optional<ErrorMessage> optionalErrorMessage = fuelingService.updateFuelingRecord(fueling, id);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFuelingRecord(@PathVariable Long id){
        Optional<ErrorMessage> optionalErrorMessage = fuelingService.deleteFuelingRecord(id);
        if (optionalErrorMessage.isPresent()){
            return new ResponseEntity<>(optionalErrorMessage.get(),HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
