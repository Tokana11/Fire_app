package com.fire_app.fire_app.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "reg_number", length = 100)
    private String regNumber;

    @ManyToOne
    private VehicleModel vehicleModel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id) &&
                Objects.equals(regNumber, vehicle.regNumber) &&
                Objects.equals(vehicleModel, vehicle.vehicleModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regNumber, vehicleModel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }


    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}
