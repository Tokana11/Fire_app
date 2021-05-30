package com.fire_app.fire_app;

import com.fire_app.fire_app.domain.model.Vehicle;
import com.fire_app.fire_app.REST.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FireAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner truckData(VehicleRepository repo) {
        return args -> {
            Vehicle veh1 = new Vehicle();
            Vehicle veh2 = new Vehicle();
            Vehicle veh3 = new Vehicle();
            veh1.setRegNumber("РВ3628РМ");
            veh2.setRegNumber("PB1231PM");
            veh3.setRegNumber("РВ4567РМ");
            veh1.setVinNumber("VIN12312312");
            veh2.setVinNumber("VIN33333333");
            veh3.setVinNumber("VIN55555555");
            repo.save(veh1);
            repo.save(veh2);
            repo.save(veh3);
        };
    }
}
