package com.fire_app.fire_app.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "repairs_history")
public class Repair {
    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "repair_description")
    private String repairDescription;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repairs = (Repair) o;
        return Double.compare(repairs.price, price) == 0 &&
                Id.equals(repairs.Id) &&
                Objects.equals(repairDescription, repairs.repairDescription) &&
                Objects.equals(date, repairs.date) &&
                Objects.equals(vehicle, repairs.vehicle);
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
