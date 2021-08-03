package com.fire_app.fire_app.service;

import com.fire_app.fire_app.domain.model.Fueling;
import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.repository.FuelingRepository;
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
public class FuelingService {
    private final FuelingRepository fuelingRepository;
    private final VehicleRepository vehicleRepository;
    private final Validator validator;

    @Autowired
    public FuelingService(FuelingRepository fuelingRepository, VehicleRepository vehicleRepository, Validator validator) {
        this.fuelingRepository = fuelingRepository;
        this.vehicleRepository = vehicleRepository;
        this.validator = validator;
    }

    public List<Fueling> getAllFuelings() {
        return fuelingRepository.findAll();
    }

    public Optional<Fueling> getFuelingById(Long id) {
        return fuelingRepository.findById(id);
    }

    public Set<Fueling> getFuelingsByVehicleId(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getFuelings();
        }
        return null;
    }

    public Set<Fueling> getFuelingsByVehicleRegNumber(String regNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findOptionalByRegNumber(regNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getFuelings();
        }
        return null;
    }

    public Optional<ErrorMessage> createFuelingRecord(Fueling fueling){
        Optional<ErrorMessage> failedValidations = validateFueling(fueling);
        if (failedValidations.isPresent()) return failedValidations;
        try {
            fuelingRepository.save(fueling);
        }catch (Exception e){
            return Optional.of(new ErrorMessage("There was a problem with saving the fueling record!"));
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> updateFuelingRecord(Fueling fueling, Long id){
        Optional<ErrorMessage> failedValidations = validateFueling(fueling);
        if (failedValidations.isPresent()) return failedValidations;
        Optional<Fueling> optionalFueling = fuelingRepository.findById(id);
        if(optionalFueling.isPresent()){
            Fueling existingFueling = optionalFueling.get();
            existingFueling.setQuantity(fueling.getQuantity());
            existingFueling.setPrice(fueling.getPrice());
            existingFueling.setDate(fueling.getDate());
            try {
                fuelingRepository.save(existingFueling);
            }catch (Exception e){
                return Optional.of(new ErrorMessage("There is a problem saving the fueling!"));
            }
        }else {
            return Optional.of(new ErrorMessage("There is no fueling record with id: " + id + " found!"));
        }
        return Optional.empty();

    }
    // TODO decide where to set the vehicle

    public Optional<ErrorMessage> deleteFuelingRecord(Long id){
        try {
            fuelingRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return Optional.of(new ErrorMessage("No fueling record with id: " + id + " exists!"));
        }
        return Optional.empty();
    }


    private Optional<ErrorMessage> validateFueling(Fueling fueling) {
        Set<ConstraintViolation<Fueling>> constraintViolations = validator.validate(fueling);
        if (!constraintViolations.isEmpty()) {
            List<String> failedValidations = new ArrayList<>();
            for (ConstraintViolation<Fueling> constraintViolation : constraintViolations) {
                failedValidations.add(constraintViolation.getMessage());
            }
            return Optional.of(new ErrorMessage("Fueling validation failure!", failedValidations));
        }
        return Optional.empty();
    }


}