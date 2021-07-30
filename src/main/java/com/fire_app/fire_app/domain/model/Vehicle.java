package com.fire_app.fire_app.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 6, max = 10)
    @Column(name = "reg_number", unique = true)
    private String regNumber;

    @NotNull
    @Size(min = 17, max = 30)
    @Column(name = "vin_number", unique = true)
    private String vinNumber;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "mileage")
    private int mileage;

    @NotNull
    @Column(name = "engine_hours_meter")
    private double engineHoursMeter;

    @OneToMany(mappedBy = "vehicle")
    private Set<Repair> repairs;

    @OneToMany(mappedBy = "vehicle")
    private Set<Service> services;

    @OneToMany(mappedBy = "vehicle")
    private Set<Fueling> fuelings;

    @ManyToOne
    @JoinColumn(name = "specifications_id")
    @JsonIgnoreProperties(value = "vehicles")
    private VehicleSpecs vehicleSpecs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id) && regNumber.equals(vehicle.regNumber) && vinNumber.equals(vehicle.vinNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regNumber, vinNumber, brand, model);
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

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getEngineHoursMeter() {
        return engineHoursMeter;
    }

    public void setEngineHoursMeter(double engineHoursMeter) {
        this.engineHoursMeter = engineHoursMeter;
    }

    public Set<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(Set<Repair> repairs) {
        this.repairs = repairs;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<Fueling> getFuelings() {
        return fuelings;
    }

    public void setFuelings(Set<Fueling> fuelings) {
        this.fuelings = fuelings;
    }

    public VehicleSpecs getVehicleSpecs() {
        return vehicleSpecs;
    }

    public void setVehicleSpecs(VehicleSpecs vehicleSpecs) {
        this.vehicleSpecs = vehicleSpecs;
    }
}
