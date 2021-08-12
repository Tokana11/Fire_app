package com.fire_app.fire_app.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotBlank
    @Column(name = "address")
    private String address;

    @Column(name = "mileage_on_return")
    private int mileageOnReturn;

    @Column(name = "engine_hours_meter_on_return")
    private double engineHoursMeterOnReturn;

    @Column(name = "kilometres")
    private int kilometres;

    @Column(name = "minutes_with_pump")
    private int minutesOnSiteWithPump;

    @Column(name = "minutes_on_site")
    private int minutesOnSite;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMileageOnReturn() {
        return mileageOnReturn;
    }

    /**
     * @param mileageOnReturn
     * Sets MileageOnReturn and initialize vehicle mileage.
     */
    public void setMileageOnReturn(int mileageOnReturn) {
        this.mileageOnReturn = mileageOnReturn;
        vehicle.setMileage(mileageOnReturn);
        int kilometres = vehicle.getMileage() - mileageOnReturn;
        setKilometres(kilometres);
    }

    public double getEngineHoursMeterOnReturn() {
        return engineHoursMeterOnReturn;
    }

    /**
     * @param engineHoursMeterOnReturn
     * Sets EngineHoursMeterOnReturn and initialize vehicle engineHoursMeter.
     */
    public void setEngineHoursMeterOnReturn(double engineHoursMeterOnReturn) {
        this.engineHoursMeterOnReturn = engineHoursMeterOnReturn;
        vehicle.setEngineHoursMeter(engineHoursMeterOnReturn);
    }

    public int getKilometres() {
        return kilometres;
    }


    public void setKilometres(int kilometres) {
        this.kilometres = kilometres;
    }

    public int getMinutesOnSiteWithPump() {
        return minutesOnSiteWithPump;
    }

    public void setMinutesOnSiteWithPump(int minutesOnSiteWithPump) {
        this.minutesOnSiteWithPump = minutesOnSiteWithPump;
    }

    public int getMinutesOnSite() {
        return minutesOnSite;
    }

    public void setMinutesOnSite(int minutesOnSite) {
        this.minutesOnSite = minutesOnSite;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
