package com.fire_app.fire_app.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specifications")
public class VehicleSpecs {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "engine_oil_type")
    private String engineOil;

    @Column(name = "engine_oil_volume")
    private double engineOilVolume;

    @Column(name = "hydraulic_oil_type")
    private String hydraulicOil;

    @Column(name = "hydraulic_oil_volume")
    private double hydraulicOilVolume;

    @Column(name = "pump_oil_type")
    private String pumpOil;

    @Column(name = "pump_oil_volume")
    private double pumpOilVolume;

    @Column(name = "differential_oil")
    private String differentialOil;

    @Column(name = "differential_oil_volume")
    private double differentialOilVolume;

    @Column(name = "gear_box_oil_type")
    private String gearBoxOil;

    @Column(name = "gear_box_oil_volume")
    private double gearBoxOilVolume;

    @Column(name = "brake_fluid_type")
    private String brakeFluid;

    @Column(name = "brake_fluid_volume")
    private double brakeFluidVolume;

    @Column(name = "anti_freeze_type")
    private String antiFreeze;

    @Column(name = "anti_freeze_volume")
    private double antiFreezeVolume;

    @Column(name = "windscreen_fluid_volume")
    private double windscreenFluidVolume;

    @Column(name = "fuel_type")
    private String fuel;

    @Column(name = "fuel_tank_volume")
    private double fuelTankVolume;

    @Column(name = "water_tank_volume")
    private int waterTankVolume;

    @Column(name = "foam_tank_volume")
    private int foamTankVolume;

    @Column(name = "fuel_filter")
    private String fuelFilter;

    @Column(name = "air_filter")
    private String airFilter;

    @Column(name = "oil_filter")
    private String oilFilter;

    @Column(name = "air_condition_filter")
    private String airConditionFilter;

    @Column(name = "tyre")
    private String tyre;

    @OneToMany(mappedBy = "vehicleSpecs")
    @JsonIgnoreProperties("vehicleSpecs")
    private Set<Vehicle> vehicles;
    //TODO is it necessary !


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleSpecs that = (VehicleSpecs) o;
        return Double.compare(that.engineOilVolume, engineOilVolume) == 0 && Double.compare(that.hydraulicOilVolume, hydraulicOilVolume) == 0 && Double.compare(that.pumpOilVolume, pumpOilVolume) == 0 && Double.compare(that.differentialOilVolume, differentialOilVolume) == 0 && Double.compare(that.gearBoxOilVolume, gearBoxOilVolume) == 0 && Double.compare(that.brakeFluidVolume, brakeFluidVolume) == 0 && Double.compare(that.antiFreezeVolume, antiFreezeVolume) == 0 && Double.compare(that.windscreenFluidVolume, windscreenFluidVolume) == 0 && Double.compare(that.fuelTankVolume, fuelTankVolume) == 0 && waterTankVolume == that.waterTankVolume && foamTankVolume == that.foamTankVolume && id.equals(that.id) && Objects.equals(engineOil, that.engineOil) && Objects.equals(hydraulicOil, that.hydraulicOil) && Objects.equals(pumpOil, that.pumpOil) && Objects.equals(differentialOil, that.differentialOil) && Objects.equals(gearBoxOil, that.gearBoxOil) && Objects.equals(brakeFluid, that.brakeFluid) && Objects.equals(antiFreeze, that.antiFreeze) && Objects.equals(fuel, that.fuel) && Objects.equals(fuelFilter, that.fuelFilter) && Objects.equals(airFilter, that.airFilter) && Objects.equals(oilFilter, that.oilFilter) && Objects.equals(airConditionFilter, that.airConditionFilter) && Objects.equals(tyre, that.tyre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, engineOil, engineOilVolume, hydraulicOil, hydraulicOilVolume, pumpOil, pumpOilVolume, differentialOil, differentialOilVolume, gearBoxOil, gearBoxOilVolume, brakeFluid, brakeFluidVolume, antiFreeze, antiFreezeVolume, windscreenFluidVolume, fuel, fuelTankVolume, waterTankVolume, foamTankVolume, fuelFilter, airFilter, oilFilter, airConditionFilter, tyre);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getWindscreenFluidVolume() {
        return windscreenFluidVolume;
    }

    public void setWindscreenFluidVolume(double windscreenFluidVolume) {
        this.windscreenFluidVolume = windscreenFluidVolume;
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

    public String getFuelFilter() {
        return fuelFilter;
    }

    public void setFuelFilter(String fuelFilter) {
        this.fuelFilter = fuelFilter;
    }

    public String getAirFilter() {
        return airFilter;
    }

    public void setAirFilter(String airFilter) {
        this.airFilter = airFilter;
    }

    public String getOilFilter() {
        return oilFilter;
    }

    public void setOilFilter(String oilFilter) {
        this.oilFilter = oilFilter;
    }

    public String getAirConditionFilter() {
        return airConditionFilter;
    }

    public void setAirConditionFilter(String airConditionFilter) {
        this.airConditionFilter = airConditionFilter;
    }

    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
