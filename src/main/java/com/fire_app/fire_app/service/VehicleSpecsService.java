package com.fire_app.fire_app.service;

import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.domain.model.VehicleSpecs;
import com.fire_app.fire_app.repository.VehicleRepository;
import com.fire_app.fire_app.repository.VehicleSpecsRepository;
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
public class VehicleSpecsService {
    private final VehicleSpecsRepository vehicleSpecsRepository;
    private final VehicleRepository vehicleRepository;
    private final Validator validator;

    @Autowired
    public VehicleSpecsService(VehicleSpecsRepository vehicleSpecsRepository, VehicleRepository vehicleRepository, Validator validator) {
        this.vehicleSpecsRepository = vehicleSpecsRepository;
        this.vehicleRepository = vehicleRepository;
        this.validator = validator;
    }

    public List<VehicleSpecs> getAllVehiclesSpecs() {
        return vehicleSpecsRepository.findAll();
    }

    public Optional<VehicleSpecs> getVehicleSpecsById(Long id) {
        return vehicleSpecsRepository.findById(id);
    }

    public VehicleSpecs getVehicleSpecByVehicleId(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getVehicleSpecs();
        }
        return null;
    }

    public VehicleSpecs getVehicleSpecsByVehicleRegNumber(String regNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findOptionalByRegNumber(regNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            return existingVehicle.getVehicleSpecs();
        }
        return null;
    }

    public Optional<ErrorMessage> createVehicleSpecsRecord(VehicleSpecs vehicleSpecs) {
        Optional<ErrorMessage> failedValidations = validateVehicleSpecs(vehicleSpecs);
        if (failedValidations.isPresent()) return failedValidations;
        try {
            vehicleSpecsRepository.save(vehicleSpecs);
        } catch (Exception e) {
            return Optional.of(new ErrorMessage("There is a problem saving the vehicle specification record!"));
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> updateVehicleSpecsRecord(VehicleSpecs vehicleSpecs, Long id) {
        Optional<ErrorMessage> failedValidations = validateVehicleSpecs(vehicleSpecs);
        if (failedValidations.isPresent()) return failedValidations;
        Optional<VehicleSpecs> optionalVehicleSpecs = vehicleSpecsRepository.findById(id);
        if (optionalVehicleSpecs.isPresent()) {
            VehicleSpecs existingVehicleSpecsRecord = optionalVehicleSpecs.get();
            existingVehicleSpecsRecord.setAirFilter(vehicleSpecs.getAirFilter());
            existingVehicleSpecsRecord.setAirConditionFilter(vehicleSpecs.getAirConditionFilter());
            existingVehicleSpecsRecord.setAntiFreeze(vehicleSpecs.getAntiFreeze());
            existingVehicleSpecsRecord.setAntiFreezeVolume(vehicleSpecs.getAntiFreezeVolume());
            existingVehicleSpecsRecord.setBrakeFluid(vehicleSpecs.getBrakeFluid());
            existingVehicleSpecsRecord.setBrakeFluidVolume(vehicleSpecs.getBrakeFluidVolume());
            existingVehicleSpecsRecord.setDifferentialOil(vehicleSpecs.getDifferentialOil());
            existingVehicleSpecsRecord.setDifferentialOilVolume(vehicleSpecs.getDifferentialOilVolume());
            existingVehicleSpecsRecord.setEngineOil(vehicleSpecs.getEngineOil());
            existingVehicleSpecsRecord.setEngineOilVolume(vehicleSpecs.getEngineOilVolume());
            existingVehicleSpecsRecord.setFuelFilter(vehicleSpecs.getFuelFilter());
            existingVehicleSpecsRecord.setFuel(vehicleSpecs.getFuel());
            existingVehicleSpecsRecord.setFuelTankVolume(vehicleSpecs.getFuelTankVolume());
            existingVehicleSpecsRecord.setFoamTankVolume(vehicleSpecs.getFoamTankVolume());
            existingVehicleSpecsRecord.setGearBoxOil(vehicleSpecs.getGearBoxOil());
            existingVehicleSpecsRecord.setGearBoxOilVolume(vehicleSpecs.getGearBoxOilVolume());
            existingVehicleSpecsRecord.setHydraulicOil(vehicleSpecs.getHydraulicOil());
            existingVehicleSpecsRecord.setHydraulicOilVolume(vehicleSpecs.getHydraulicOilVolume());
            existingVehicleSpecsRecord.setOilFilter(vehicleSpecs.getOilFilter());
            existingVehicleSpecsRecord.setPumpOil(vehicleSpecs.getPumpOil());
            existingVehicleSpecsRecord.setPumpOilVolume(vehicleSpecs.getPumpOilVolume());
            existingVehicleSpecsRecord.setTyre(vehicleSpecs.getTyre());
            existingVehicleSpecsRecord.setWaterTankVolume(vehicleSpecs.getWaterTankVolume());
            existingVehicleSpecsRecord.setWindscreenFluidVolume(vehicleSpecs.getWindscreenFluidVolume());
            try {
                vehicleSpecsRepository.save(existingVehicleSpecsRecord);
            } catch (Exception e) {
                return Optional.of(new ErrorMessage("There is a problem updating the vehicle specification record!"));
            }
        }
        return Optional.empty();
    }

    public Optional<ErrorMessage> deleteVehicleSpecsRecord(Long id){
        try {
            vehicleSpecsRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return Optional.of(new ErrorMessage("No vehicle specification record with id: "+ id + " exists! "));
        }
        return Optional.empty();
    }

    private Optional<ErrorMessage> validateVehicleSpecs(VehicleSpecs vehicleSpecs) {
        Set<ConstraintViolation<VehicleSpecs>> constraintViolations = validator.validate(vehicleSpecs);
        if (!constraintViolations.isEmpty()) {
            List<String> failedValidations = new ArrayList<>();
            for (ConstraintViolation<VehicleSpecs> constraintViolation : constraintViolations) {
                failedValidations.add(constraintViolation.getMessage());
            }
            return Optional.of(new ErrorMessage("Vehicle specification validation failure!", failedValidations));
        }
        return Optional.empty();
    }
}
