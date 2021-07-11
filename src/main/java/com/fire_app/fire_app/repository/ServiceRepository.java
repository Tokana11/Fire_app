package com.fire_app.fire_app.repository;

import com.fire_app.fire_app.domain.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    Set<Service> findServiceByVehicleId(Long id);
    Set<Service> findServiceByVehicleRegNumber(String regNumber);
}
