package com.fire_app.fire_app.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "fueling")
public class Fueling {
    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "quantity")
    private double quantity;

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
        Fueling fueling = (Fueling) o;
        return Double.compare(fueling.quantity, quantity) == 0 &&
                Double.compare(fueling.price, price) == 0 &&
                Id.equals(fueling.Id) &&
                Objects.equals(date, fueling.date) &&
                Objects.equals(vehicle, fueling.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, quantity, price, date, vehicle);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
