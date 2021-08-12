package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.domain.model.Trip;
import com.fire_app.fire_app.service.TripService;
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
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public ResponseEntity<List<Trip>> findAll() {
        return new ResponseEntity<>(tripService.getAllTrips(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTripById(@PathVariable Long id) {
        Optional<Trip> optionalTrip = tripService.getTripById(id);
        if (optionalTrip.isPresent()) {
            return new ResponseEntity<>(optionalTrip.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorMessage("Trip record with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byVehicleId/{id}")
    public ResponseEntity<?> findTripsByVehicleId(@PathVariable Long id) {
        Optional<Set<Trip>> trips = Optional.ofNullable(tripService.getTripsByVehicleId(id));
        if (trips.isPresent()) {
            return new ResponseEntity<>(trips, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No vehicle with id: " + id + " found!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/byVehicleRegNumber/{regNumber}")
    public ResponseEntity<?> findTripsByVehicleRegNumber(@PathVariable(name = "regNumber") String regNumber) {
        Optional<Set<Trip>> trips = Optional.ofNullable(tripService.getTripsByVehicleRegNumber(regNumber));
        if (trips.isPresent()) {
            return new ResponseEntity<>(trips, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No vehicle with register number: " + regNumber + " found!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTripRecord(@RequestBody Trip trip) {
        Optional<ErrorMessage> optionalErrorMessage = tripService.createTripRecord(trip);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(trip.getId(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTripRecord(@RequestBody Trip trip, @PathVariable Long id) {
        Optional<ErrorMessage> optionalErrorMessage = tripService.updateTripRecord(trip, id);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErrorMessage> deleteTripRecord(@PathVariable Long id) {
        Optional<ErrorMessage> optionalErrorMessage = tripService.deleteTripRecord(id);
        if (optionalErrorMessage.isPresent()) {
            return new ResponseEntity<>(optionalErrorMessage.get(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    //TODO test all endpoints
}
