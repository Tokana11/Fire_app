package com.fire_app.fire_app.repository;

import com.fire_app.fire_app.domain.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RepairsRepository extends JpaRepository<Repair,Long> {

    Set<Repair> findRepairsByVehicleRegNumber (String regNumber);
    Set<Repair> findRepairsByVehicleId(Long id);
}
