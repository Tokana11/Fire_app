package com.fire_app.fire_app.repository;

import com.fire_app.fire_app.domain.model.Fueling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelingRepository extends JpaRepository<Fueling, Long> {
}
