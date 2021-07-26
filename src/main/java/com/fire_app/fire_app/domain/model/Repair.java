package com.fire_app.fire_app.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "repair")
public class Repair {
    @Id
    @GeneratedValue
    private Long Id;

    @NotBlank
    @Column(name = "repair_description")
    private String repairDescription;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return Double.compare(repair.price, price) == 0 && Id.equals(repair.Id) && Objects.equals(repairDescription, repair.repairDescription) && Objects.equals(date, repair.date) && Objects.equals(vehicle, repair.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, repairDescription, price, date, vehicle);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
