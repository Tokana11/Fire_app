package com.fire_app.fire_app.repository;

import com.fire_app.fire_app.domain.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findOptionalByRegNumber (String regNumber);
}
