package com.fire_app.fire_app.service;

import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.repository.ServiceRepository;
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
public class ServiceModelService {

    private final ServiceRepository serviceRepository;
    private final VehicleRepository vehicleRepository;
    private final Validator validator;

    @Autowired
    public ServiceModelService(ServiceRepository serviceRepository, VehicleRepository vehicleRepository, Validator validator) {
        this.serviceRepository = serviceRepository;
        this.vehicleRepository = vehicleRepository;
        this.validator = validator;
    }

    public List<com.fire_app.fire_app.domain.model.Service> getAllServiceRecords() {
        return serviceRepository.findAll();
    }

    public Optional<com.fire_app.fire_app.domain.model.Service> getServiceRecordById(Long id) {
        return serviceRepository.findById(id);
    }

    public Set<com.fire_app.fire_app.domain.model.Service> getServicesByVehicleId(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle exisingVehicle = optionalVehicle.get();
            return exisingVehicle.getServices();
        }
        return null;
    }

    public Set<com.fire_app.fire_app.domain.model.Service> getServicesByVehicleRegNumber(String regNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findOptionalByRegNumber(regNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getServices();
        }
        return null;
    }

    public Optional<ErrorMessage> createServiceRecord(com.fire_app.fire_app.domain.model.Service service) {
        Optional<ErrorMessage> failedValidations = validateService(service);
        if (failedValidations.isPresent()) return failedValidations;
        try {
            serviceRepository.save(service);
        } catch (Exception e) {
            return Optional.of(new ErrorMessage("There was a problem saving the service record!"));
        }
        return Optional.empty();
    }

    // TODO write update Service logic

    public Optional<ErrorMessage> deleteService(Long id) {
        try {
            serviceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.of(new ErrorMessage("No service record with id: " + id + " exists!"));
        }
        return Optional.empty();
    }

    private Optional<ErrorMessage> validateService(com.fire_app.fire_app.domain.model.Service service) {
        Set<ConstraintViolation<com.fire_app.fire_app.domain.model.Service>> constraintViolations = validator.validate(service);
        if (!constraintViolations.isEmpty()) {
            List<String> failedValidations = new ArrayList<>();
            for (ConstraintViolation<com.fire_app.fire_app.domain.model.Service> constraintViolation : constraintViolations) {
                failedValidations.add(constraintViolation.getMessage());
            }
            return Optional.of(new ErrorMessage("Service validation failure!", failedValidations));
        }
        return Optional.empty();
    }
}
