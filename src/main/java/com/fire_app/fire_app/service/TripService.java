package com.fire_app.fire_app.service;

import com.fire_app.fire_app.domain.model.Trip;
import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.repository.TripRepository;
import com.fire_app.fire_app.repository.VehicleRepository;
import com.fire_app.fire_app.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;
    private final Validator validator;

    @Autowired
    public TripService(TripRepository tripRepository, VehicleRepository vehicleRepository, Validator validator) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
        this.validator = validator;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public Set<Trip> getTripsByVehicleId(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getTrips();
        }
        return null;
    }

    public Set<Trip> getTripsByVehicleRegNumber(String regNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findOptionalByRegNumber(regNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getTrips();
        }
        return null;
    }

    public Optional<ErrorMessage> createTripRecord(Trip trip) {
        Optional<ErrorMessage> failedValidations = validateTrip(trip);
        if (failedValidations.isPresent()) return failedValidations;
        try {
            tripRepository.save(trip);
        } catch (Exception e) {
            return Optional.of(new ErrorMessage("There was a problem saving the trip record!"));
        }
        return Optional.empty();
    }


    public Optional<ErrorMessage> updateTripRecord(Trip trip, Long id) {
        Optional<ErrorMessage> failedValidations = validateTrip(trip);
        if (failedValidations.isPresent()) return failedValidations;
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if (optionalTrip.isPresent()) {
            Trip existingTrip = optionalTrip.get();
            existingTrip.setDate(trip.getDate());
            existingTrip.setAddress(trip.getAddress());
            existingTrip.setMileageOnReturn(trip.getMileageOnReturn());
            existingTrip.setEngineHoursMeterOnReturn(trip.getEngineHoursMeterOnReturn());
            existingTrip.setMinutesOnSiteWithPump(trip.getMinutesOnSiteWithPump());
            existingTrip.setMinutesOnSite(trip.getMinutesOnSite());
            existingTrip.setVehicle(trip.getVehicle());
            try {
                tripRepository.save(trip);
            } catch (Exception e) {
                return Optional.of(new ErrorMessage("There was a problem saving the trip record!"));
            }
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> deleteTripRecord(Long id){
        try {
            tripRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return Optional.of(new ErrorMessage("No trip record with id: " + id + " exists!"));
        }
        return Optional.empty();
    }

    private Optional<ErrorMessage> validateTrip(Trip trip) {
        Set<ConstraintViolation<Trip>> constraintViolations = validator.validate(trip);
        if (!constraintViolations.isEmpty()) {
            List<String> failedValidations = new ArrayList<>();
            for (ConstraintViolation<Trip> constraintViolation : constraintViolations) {
                failedValidations.add(constraintViolation.getMessage());
            }
            return Optional.of(new ErrorMessage("Trip validation failure!", failedValidations));
        }
        return Optional.empty();
    }
}
