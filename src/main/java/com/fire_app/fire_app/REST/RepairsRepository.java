package com.fire_app.fire_app.REST;

import com.fire_app.fire_app.domain.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RepairsRepository extends JpaRepository<Repair,Long> {

    Set<Repair> findRepairsByVehicleRegNumber (String regNumber);
}
