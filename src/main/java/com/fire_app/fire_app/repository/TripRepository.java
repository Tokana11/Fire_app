package com.fire_app.fire_app.repository;

import com.fire_app.fire_app.domain.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
