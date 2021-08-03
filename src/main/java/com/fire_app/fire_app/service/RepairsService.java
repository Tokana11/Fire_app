package com.fire_app.fire_app.service;

import com.fire_app.fire_app.domain.model.Repair;
import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.repository.RepairsRepository;
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
public class RepairsService {
    private final RepairsRepository repairsRepository;
    private final VehicleRepository vehicleRepository;
    private final Validator validator;

    @Autowired
    public RepairsService(RepairsRepository repairsRepository, VehicleRepository vehicleRepository, Validator validator) {
        this.repairsRepository = repairsRepository;
        this.vehicleRepository = vehicleRepository;
        this.validator = validator;
    }

    public List<Repair> getAllRepairs() {
        return repairsRepository.findAll();
    }

    public Optional<Repair> getRepairById(Long id) {
        return repairsRepository.findById(id);
    }

    public Set<Repair> getRepairsByVehicleId(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getRepairs();
        }
        return null;
    }

    public Set<Repair> getRepairByVehicleRegNumber(String regNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findOptionalByRegNumber(regNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getRepairs();
        }
        return null;
    }

    public Optional<ErrorMessage> createRepairRecord(Repair repair) {
        Optional<ErrorMessage> failedValidations = validateRepair(repair);
        if (failedValidations.isPresent()) return failedValidations;
        try {
            repairsRepository.save(repair);
        } catch (Exception e) {
            return Optional.of(new ErrorMessage("There was a problem with saving the repair record!"));
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> updateRepairRecord(Repair repair, Long id) {
        Optional<ErrorMessage> failedValidations = validateRepair(repair);
        if (failedValidations.isPresent()) return failedValidations;
        Optional<Repair> optionalRepair = repairsRepository.findById(id);
        if (optionalRepair.isPresent()) {
            Repair existingRepair = optionalRepair.get();
            existingRepair.setVehicle(repair.getVehicle());
            existingRepair.setRepairDescription(repair.getRepairDescription());
            existingRepair.setDate(repair.getDate());
            existingRepair.setPrice(repair.getPrice());
            try {
                repairsRepository.save(existingRepair);
            } catch (Exception e) {
               return Optional.of(new ErrorMessage("There is a problem saving the repair!"));
            }
        }else {
            return Optional.of(new ErrorMessage("There is no repair record with id: " + id + " found!"));
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> deleteRepairRecord(Long id) {
        try {
            repairsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.of(new ErrorMessage("No repair record with id: " + id + " exists!"));
        }
        return Optional.empty();
    }

    private Optional<ErrorMessage> validateRepair(Repair repair) {
        Set<ConstraintViolation<Repair>> constraintViolations = validator.validate(repair);
        if (!constraintViolations.isEmpty()) {
            List<String> failedValidations = new ArrayList<>();
            for (ConstraintViolation<Repair> constraintViolation : constraintViolations) {
                failedValidations.add(constraintViolation.getMessage());
            }
            return Optional.of(new ErrorMessage("Repair validation failure!", failedValidations));
        }
        return Optional.empty();
    }

}
