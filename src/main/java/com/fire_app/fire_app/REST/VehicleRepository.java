package com.fire_app.fire_app.REST;

import com.fire_app.fire_app.domain.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByRegNumber(String regNumber);
}
