package com.fire_app.fire_app;

import com.fire_app.fire_app.domain.model.Repair;
import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.domain.model.VehicleSpecs;
import com.fire_app.fire_app.repository.RepairsRepository;
import com.fire_app.fire_app.repository.VehicleRepository;
import com.fire_app.fire_app.repository.VehicleSpecsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class FireAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner truckData(VehicleRepository repo, VehicleSpecsRepository repo1) {
        return args -> {


            Vehicle veh1 = new Vehicle();
            veh1.setRegNumber("РВ3628РМ");
            veh1.setVinNumber("VIN1111111111111111");
            veh1.setBrand("Renault");
            veh1.setModel("Midlum 270 dxi");
            veh1.setInsuranceNumber("Insurance111111");


            Vehicle veh2 = new Vehicle();
            veh2.setRegNumber("РВ1836РР");
            veh2.setVinNumber("VIN3333333333333333");
            veh2.setBrand("Iveco");
            veh2.setModel("Daily 65С15");
            veh2.setInsuranceNumber("Insurance3333333");

            Vehicle veh3 = new Vehicle();
            veh3.setRegNumber("РВ3628AB");
            veh3.setVinNumber("VIN5555555555555555");
            veh3.setBrand("Scania");
            veh3.setModel("P360");
            veh3.setInsuranceNumber("Insurance5555");

            Vehicle veh4 = new Vehicle();
            veh4.setRegNumber("РВ1837РР");
            veh4.setVinNumber("VIN777777777777777");
            veh4.setBrand("Iveco");
            veh4.setModel("Euro Cargo");
            veh4.setInsuranceNumber("Insurance777777");

            Vehicle veh5 = new Vehicle();
            veh5.setRegNumber("РВ7608СВ");
            veh5.setVinNumber("VIN99999999999999");
            veh5.setBrand("Iveco");
            veh5.setModel("Daily 65C17В");
            veh5.setInsuranceNumber("Insurance999999");

            VehicleSpecs renaultSpecs = new VehicleSpecs();
            renaultSpecs.setEngineOil("SAE 5W30/ SAE 10W40");
            renaultSpecs.setEngineOilVolume(27.5);
            renaultSpecs.setGearBoxOil("70W80-GL 4");
            renaultSpecs.setGearBoxOilVolume(8.0);
            renaultSpecs.setDifferentialOil("SAE 80W90-GL 5 / SAE 75W90-GL 5");
            renaultSpecs.setDifferentialOilVolume(8.0);
            renaultSpecs.setAntiFreeze("G-12 red-color");
            renaultSpecs.setAntiFreezeVolume(22.5);
            renaultSpecs.setFuel("Diesel");
            renaultSpecs.setFuelTankVolume(120);
            renaultSpecs.setWaterTankVolume(3000);
            renaultSpecs.setFoamTankVolume(300);
            renaultSpecs.setTyre("Michelin 22.5 drive tires");
            renaultSpecs.setPumpOil("Mobil SHC 607");
            renaultSpecs.setPumpOilVolume(2.0);
            renaultSpecs.setBrakeFluid("DOT 4");
            renaultSpecs.setBrakeFluidVolume(0.5);
            renaultSpecs.setAirFilter("Air filter");
            renaultSpecs.setFuelFilter("Fuel filter");
            renaultSpecs.setOilFilter("Oil filter");
            renaultSpecs.setAirConditionFilter("Air conditioner filter");
            repo1.save(renaultSpecs);

            VehicleSpecs ivecoDailySpecs = new VehicleSpecs();
            ivecoDailySpecs.setEngineOil("SAE 5W30");
            ivecoDailySpecs.setEngineOilVolume(7);
            ivecoDailySpecs.setPumpOil("SAE 75W80");
            ivecoDailySpecs.setPumpOilVolume(4);
            ivecoDailySpecs.setDifferentialOil("SAE 85W140");
            ivecoDailySpecs.setDifferentialOilVolume(3);
            ivecoDailySpecs.setGearBoxOil("SAE 75W80");
            ivecoDailySpecs.setGearBoxOilVolume(2);
            ivecoDailySpecs.setBrakeFluid("DOT 4");
            ivecoDailySpecs.setBrakeFluidVolume(1.5);
            ivecoDailySpecs.setAntiFreeze("Blue color");
            ivecoDailySpecs.setAntiFreezeVolume(9);
            ivecoDailySpecs.setWindscreenFluidVolume(7.5);
            ivecoDailySpecs.setFuel("Diesel");
            ivecoDailySpecs.setFuelTankVolume(90);
            ivecoDailySpecs.setWaterTankVolume(1000);
            ivecoDailySpecs.setFoamTankVolume(100);
            ivecoDailySpecs.setFuelFilter("Fuel filter");
            ivecoDailySpecs.setAirFilter("Air filter");
            ivecoDailySpecs.setAirConditionFilter("Air conditioner filter");
            ivecoDailySpecs.setTyre("Debica navigator R16");
            repo1.save(ivecoDailySpecs);

            VehicleSpecs scaniaSpecs = new VehicleSpecs();
            scaniaSpecs.setEngineOil("SAE 5W30 / SAE 10W40");
            scaniaSpecs.setEngineOilVolume(40);
            scaniaSpecs.setPumpOil("Mobil SHC 627");
            scaniaSpecs.setPumpOilVolume(2);
            scaniaSpecs.setDifferentialOil("SAE 80W90-GL5 / SAE 75W90-GL5");
            scaniaSpecs.setDifferentialOilVolume(15);
            scaniaSpecs.setGearBoxOil("70W80- GL4");
            scaniaSpecs.setGearBoxOilVolume(10);
            scaniaSpecs.setBrakeFluid("DOT 4");
            scaniaSpecs.setBrakeFluidVolume(0.5);
            scaniaSpecs.setAntiFreeze("G12 Red color");
            scaniaSpecs.setAntiFreezeVolume(30);
            scaniaSpecs.setWindscreenFluidVolume(15);
            scaniaSpecs.setFuel("Diesel");
            scaniaSpecs.setFuelTankVolume(150);
            scaniaSpecs.setWaterTankVolume(8000);
            scaniaSpecs.setFoamTankVolume(800);
            scaniaSpecs.setFuelFilter("Fuel filter");
            scaniaSpecs.setAirFilter("Air filter");
            scaniaSpecs.setAirConditionFilter("Air conditioner filter");
            scaniaSpecs.setTyre("Good year  R22.5");
            repo1.save(scaniaSpecs);

            veh1.setVehicleSpecs(renaultSpecs);
            veh2.setVehicleSpecs(ivecoDailySpecs);
            veh3.setVehicleSpecs(scaniaSpecs);
            veh5.setVehicleSpecs(ivecoDailySpecs);

            repo.save(veh1);
            repo.save(veh2);
            repo.save(veh3);
            repo.save(veh4);
            repo.save(veh5);


        };
    }

    @Bean
    public CommandLineRunner repairsData(RepairsRepository repo1, VehicleRepository repo) {
        return args -> {

            Vehicle veh1 = new Vehicle();
            veh1.setRegNumber("PB0007PM");
            veh1.setVinNumber("12345678910111213141556646");
            veh1.setBrand("ZIL");
            veh1.setModel("130");
            veh1.setInsuranceNumber("Insurance999999");
            repo.save(veh1);

            Vehicle veh2 = new Vehicle();
            veh2.setRegNumber("РВ9999РМ");
            veh2.setVinNumber("12300000004560000078900");
            veh2.setBrand("Magerus");
            veh2.setModel("Doiz");
            veh2.setInsuranceNumber("Insurance999999");
            repo.save(veh2);


            Repair repairVeh1 = new Repair();
            repairVeh1.setRepairDescription("Some repair!");
            repairVeh1.setPrice(130.69);
            repairVeh1.setDate(new Date(new SimpleDateFormat("yyyyMMdd").parse("20210606").toInstant().toEpochMilli()));
            // TODO Create setDate (); parser - create custom exception handler
            repairVeh1.setVehicle(veh1);


            Repair secondRepairVeh1 = new Repair();
            secondRepairVeh1.setRepairDescription("Some repair 1.0!");
            secondRepairVeh1.setPrice(70.00);
            secondRepairVeh1.setDate(new Date(new SimpleDateFormat("yyyyMMdd").parse("20210607").toInstant().toEpochMilli()));
            secondRepairVeh1.setVehicle(veh1);

            Repair repairVeh2 = new Repair();
            repairVeh2.setRepairDescription("Exhaust pipe repair.");
            repairVeh2.setPrice(39.90);
            repairVeh2.setDate(new Date(new SimpleDateFormat("yyyyMMdd").parse("20210728").toInstant().toEpochMilli()));
            repairVeh2.setVehicle(veh2);

            repo1.save(repairVeh1);
            repo1.save(secondRepairVeh1);
            repo1.save(repairVeh2);
        };
    }
}
