package com.fire_app.fire_app.domain.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "reg_number", unique = true, nullable = false, length = 100)
    private String regNumber;

    @Column(name = "vin_number", unique = true, nullable = false, length = 100)
    private String vinNumber;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "model_name", length = 100)
    private String modelName;

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

    @Column(name = "differential_oil", length = 100)
    private String differentialOil;

    @Column(name = "differential_oil_volume")
    private double differentialOilVolume;

    @Column(name = "gear_box_oil_type", length = 100)
    private String gearBoxOil;

    @Column(name = "gear_box_oil_volume")
    private double gearBoxOilVolume;

    @Column(name = "brake_fluid_type", length = 100)
    private String brakeFluid;

    @Column(name = "brake_fluid_volume", length = 100)
    double brakeFluidVolume;

    @Column(name = "anti_freeze_type")
    private String antiFreeze;

    @Column(name = "anti_freeze_volume")
    private double antiFreezeVolume;

    @Column(name = "fuel_type")
    private String fuel;

    @Column(name = "fuel_tank_volume")
    private double fuelTankVolume;

    @Column(name = "water_tank_volume")
    private int waterTankVolume;

    @Column(name = "foam_tank_volume")
    private int foamTankVolume;

    @Column(name = "tyre")
    private String tyre;

    @OneToMany(mappedBy = "vehicle")
    private Set<Repair> repairs;

    @OneToMany(mappedBy = "vehicle")
    private Set<Service> services;

    @OneToMany(mappedBy = "vehicle")
    private Set<Fueling> fuelings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.engineOilVolume, engineOilVolume) == 0 && Double.compare(vehicle.hydraulicOilVolume, hydraulicOilVolume) == 0 && Double.compare(vehicle.pumpOilVolume, pumpOilVolume) == 0 && Double.compare(vehicle.differentialOilVolume, differentialOilVolume) == 0 && Double.compare(vehicle.gearBoxOilVolume, gearBoxOilVolume) == 0 && Double.compare(vehicle.brakeFluidVolume, brakeFluidVolume) == 0 && Double.compare(vehicle.antiFreezeVolume, antiFreezeVolume) == 0 && Double.compare(vehicle.fuelTankVolume, fuelTankVolume) == 0 && waterTankVolume == vehicle.waterTankVolume && foamTankVolume == vehicle.foamTankVolume && id.equals(vehicle.id) && regNumber.equals(vehicle.regNumber) && vinNumber.equals(vehicle.vinNumber) && Objects.equals(brand, vehicle.brand) && Objects.equals(modelName, vehicle.modelName) && Objects.equals(engineOil, vehicle.engineOil) && Objects.equals(hydraulicOil, vehicle.hydraulicOil) && Objects.equals(pumpOil, vehicle.pumpOil) && Objects.equals(differentialOil, vehicle.differentialOil) && Objects.equals(gearBoxOil, vehicle.gearBoxOil) && Objects.equals(brakeFluid, vehicle.brakeFluid) && Objects.equals(antiFreeze, vehicle.antiFreeze) && Objects.equals(fuel, vehicle.fuel) && Objects.equals(tyre, vehicle.tyre) && Objects.equals(repairs, vehicle.repairs) && Objects.equals(services, vehicle.services) && Objects.equals(fuelings, vehicle.fuelings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regNumber, vinNumber, brand, modelName, engineOil, engineOilVolume, hydraulicOil, hydraulicOilVolume, pumpOil, pumpOilVolume, differentialOil, differentialOilVolume, gearBoxOil, gearBoxOilVolume, brakeFluid, brakeFluidVolume, antiFreeze, antiFreezeVolume, fuel, fuelTankVolume, waterTankVolume, foamTankVolume, tyre, repairs, services, fuelings);
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getDifferentialOil() {
        return differentialOil;
    }

    public void setDifferentialOil(String differentialOil) {
        this.differentialOil = differentialOil;
    }

    public double getDifferentialOilVolume() {
        return differentialOilVolume;
    }

    public void setDifferentialOilVolume(double differentialOilVolume) {
        this.differentialOilVolume = differentialOilVolume;
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

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getFuelTankVolume() {
        return fuelTankVolume;
    }

    public void setFuelTankVolume(double fuelTankVolume) {
        this.fuelTankVolume = fuelTankVolume;
    }

    public int getWaterTankVolume() {
        return waterTankVolume;
    }

    public void setWaterTankVolume(int waterTankVolume) {
        this.waterTankVolume = waterTankVolume;
    }

    public int getFoamTankVolume() {
        return foamTankVolume;
    }

    public void setFoamTankVolume(int foamTankVolume) {
        this.foamTankVolume = foamTankVolume;
    }

    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
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
}
