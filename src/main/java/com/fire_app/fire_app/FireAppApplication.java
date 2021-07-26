package com.fire_app.fire_app;

import com.fire_app.fire_app.repository.RepairsRepository;
import com.fire_app.fire_app.domain.model.Repair;
import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.repository.VehicleRepository;
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
    public CommandLineRunner truckData(VehicleRepository repo, RepairsRepository repo1) {
        return args -> {
            Vehicle veh1 = new Vehicle();
            Vehicle veh2 = new Vehicle();
            Vehicle veh3 = new Vehicle();

            veh1.setRegNumber("РВ3628РМ");
            veh2.setRegNumber("PB1231PM");
            veh3.setRegNumber("РВ4567РМ");
            veh1.setVinNumber("VIN123123121221212121212212121");
            veh2.setVinNumber("VIN333333333333333333333333333");
            veh3.setVinNumber("VIN555555555555555555555555555");

            repo.save(veh1);
            repo.save(veh2);
            repo.save(veh3);


        };
    }

    @Bean
    public CommandLineRunner repairsData(RepairsRepository repo1, VehicleRepository repo) {
        return args -> {

            Vehicle veh1 = new Vehicle();
            veh1.setRegNumber("PB0007PM");
            veh1.setVinNumber("123456789101112131415");
            repo.save(veh1);


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

            repo1.save(repairVeh1);
            repo1.save(secondRepairVeh1);
        };
    }
}
