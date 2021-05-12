package com.fire_app.fire_app.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "service_history")
public class Service {
    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "engine_oil_type", length = 100)
    private String engineOil;

    @Column(name = "engine_oil_volume")
    private double engineOilVolume;

    @Column(name = "hydraulic_oil_type", length = 100)
    private String hydraulicOil;

    @Column(name = "hydraulic_oil_volume")
    private double hydraulicOilVolume;

    @Column(name = "pump_oil_type", length = 100)
    private String pumpOil;

    @Column(name = "pump_oil_volume")
    private double pumpOilVolume;

    @Column(name = "diferential_oil", length = 100)
    private String diferentialOil;

    @Column(name = "diferential_oil_volume")
    private double diferentialOilVolume;

    @Column(name = "gear_box_oil_type", length = 100)
    private String gearBoxOil;

    @Column(name = "gear_box_oil_volume")
    private double gearBoxOilVolume;

    @Column(name = "brake_fluid_type", length = 100)
    private String brakeFluid;

    @Column(name = "brake_fluid_volume", length = 100)
    double brakeFluidVolume;

    @Column(name = "antifreez_type")
    private String antiFreeze;

    @Column(name = "antifreez_volume")
    private double antiFreezeVolume;

    @ManyToOne
    private Vehicle vehicle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Double.compare(service.engineOilVolume, engineOilVolume) == 0 &&
                Double.compare(service.hydraulicOilVolume, hydraulicOilVolume) == 0 &&
                Double.compare(service.pumpOilVolume, pumpOilVolume) == 0 &&
                Double.compare(service.diferentialOilVolume, diferentialOilVolume) == 0 &&
                Double.compare(service.gearBoxOilVolume, gearBoxOilVolume) == 0 &&
                Double.compare(service.brakeFluidVolume, brakeFluidVolume) == 0 &&
                Double.compare(service.antiFreezeVolume, antiFreezeVolume) == 0 &&
                Id.equals(service.Id) &&
                Objects.equals(engineOil, service.engineOil) &&
                Objects.equals(hydraulicOil, service.hydraulicOil) &&
                Objects.equals(pumpOil, service.pumpOil) &&
                Objects.equals(diferentialOil, service.diferentialOil) &&
                Objects.equals(gearBoxOil, service.gearBoxOil) &&
                Objects.equals(brakeFluid, service.brakeFluid) &&
                Objects.equals(antiFreeze, service.antiFreeze) &&
                Objects.equals(vehicle, service.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, engineOil, engineOilVolume, hydraulicOil, hydraulicOilVolume, pumpOil, pumpOilVolume, diferentialOil, diferentialOilVolume, gearBoxOil, gearBoxOilVolume, brakeFluid, brakeFluidVolume, antiFreeze, antiFreezeVolume, vehicle);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEngineOil() {
        return engineOil;
    }

    public void setEngineOil(String engineOil) {
        this.engineOil = engineOil;
    }

    public double getEngineOilVolume() {
        return engineOilVolume;
    }

    public void setEngineOilVolume(double engineOilVolume) {
        this.engineOilVolume = engineOilVolume;
    }

    public String getHydraulicOil() {
        return hydraulicOil;
    }

    public void setHydraulicOil(String hydraulicOil) {
        this.hydraulicOil = hydraulicOil;
    }

    public double getHydraulicOilVolume() {
        return hydraulicOilVolume;
    }

    public void setHydraulicOilVolume(double hydraulicOilVolume) {
        this.hydraulicOilVolume = hydraulicOilVolume;
    }

    public String getPumpOil() {
        return pumpOil;
    }

    public void setPumpOil(String pumpOil) {
        this.pumpOil = pumpOil;
    }

    public double getPumpOilVolume() {
        return pumpOilVolume;
    }

    public void setPumpOilVolume(double pumpOilVolume) {
        this.pumpOilVolume = pumpOilVolume;
    }

    public String getDiferentialOil() {
        return diferentialOil;
    }

    public void setDiferentialOil(String diferentialOil) {
        this.diferentialOil = diferentialOil;
    }

    public double getDiferentialOilVolume() {
        return diferentialOilVolume;
    }

    public void setDiferentialOilVolume(double diferentialOilVolume) {
        this.diferentialOilVolume = diferentialOilVolume;
    }

    public String getGearBoxOil() {
        return gearBoxOil;
    }

    public void setGearBoxOil(String gearBoxOil) {
        this.gearBoxOil = gearBoxOil;
    }

    public double getGearBoxOilVolume() {
        return gearBoxOilVolume;
    }

    public void setGearBoxOilVolume(double gearBoxOilVolume) {
        this.gearBoxOilVolume = gearBoxOilVolume;
    }

    public String getBrakeFluid() {
        return brakeFluid;
    }

    public void setBrakeFluid(String brakeFluid) {
        this.brakeFluid = brakeFluid;
    }

    public double getBrakeFluidVolume() {
        return brakeFluidVolume;
    }

    public void setBrakeFluidVolume(double brakeFluidVolume) {
        this.brakeFluidVolume = brakeFluidVolume;
    }

    public String getAntiFreeze() {
        return antiFreeze;
    }

    public void setAntiFreeze(String antiFreeze) {
        this.antiFreeze = antiFreeze;
    }

    public double getAntiFreezeVolume() {
        return antiFreezeVolume;
    }

    public void setAntiFreezeVolume(double antiFreezeVolume) {
        this.antiFreezeVolume = antiFreezeVolume;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
