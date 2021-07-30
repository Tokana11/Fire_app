package com.fire_app.fire_app.service;

import com.fire_app.fire_app.domain.model.Vehicle;
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
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final Validator validator;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, Validator validator) {
        this.vehicleRepository = vehicleRepository;
        this.validator = validator;
    }


    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Optional<Vehicle> getVehicleByRegNumber(String regNumber) {
        return vehicleRepository.findOptionalByRegNumber(regNumber);
    }

    public Optional<ErrorMessage> createVehicle(Vehicle vehicle) {
        Optional<ErrorMessage> failedValidations = validateVehicle(vehicle);
        if (failedValidations.isPresent()) return failedValidations;
        try {
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            return Optional.of(new ErrorMessage("There was a problem with saving the vehicle!"));
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> updateVehicle(Vehicle vehicle, Long id) {
        Optional<ErrorMessage> failedValidations = validateVehicle(vehicle);
        if (failedValidations.isPresent()) return failedValidations;
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            existingVehicle.setRegNumber(vehicle.getRegNumber());
            existingVehicle.setVinNumber(vehicle.getVinNumber());
            existingVehicle.setInsuranceNumber(vehicle.getInsuranceNumber());
            existingVehicle.setBrand(vehicle.getBrand());
            existingVehicle.setModel(vehicle.getModel());
            existingVehicle.setMileage(vehicle.getMileage());
            existingVehicle.setEngineHoursMeter(vehicle.getEngineHoursMeter());
            try {
                vehicleRepository.save(existingVehicle);
            } catch (Exception e) {
                return Optional.of(new ErrorMessage("There was a problem with saving the vehicle!"));
            }
        } else {
            return Optional.of(new ErrorMessage("There is no vehicle with id: " + id + " found!"));
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> deleteVehicle(Long id) {
        try {
            vehicleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.of(new ErrorMessage("No Vehicle entity with id:" + id + " exists! "));
        }
        return Optional.empty();
    }

    private Optional<ErrorMessage> validateVehicle(Vehicle vehicle) {
        Set<ConstraintViolation<Vehicle>> constraintViolations = validator.validate(vehicle);
        if (!constraintViolations.isEmpty()) {
            List<String> failedValidations = new ArrayList<>();
            for (ConstraintViolation<Vehicle> constraintViolation : constraintViolations) {
                failedValidations.add(constraintViolation.getMessage());
            }
            return Optional.of(new ErrorMessage("Vehicle validation failure!", failedValidations));
        }
        return Optional.empty();
    }


}
