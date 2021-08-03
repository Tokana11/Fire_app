package com.fire_app.fire_app.repository;

import com.fire_app.fire_app.domain.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairsRepository extends JpaRepository<Repair,Long> {

}
