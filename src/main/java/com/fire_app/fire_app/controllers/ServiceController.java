package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.repository.VehicleRepository;
import com.fire_app.fire_app.repository.ServiceRepository;
import com.fire_app.fire_app.domain.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping("/service")
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public ServiceController(ServiceRepository serviceRepository, VehicleRepository vehicleRepository) {
        this.serviceRepository = serviceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Service>> findAll() {
        List<Service> services = serviceRepository.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable(name = "id") Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Set<Service>> getServicesByVehicleId(@PathVariable(name = "id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Service> services = serviceRepository.findServiceByVehicleId(id);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/vehicle/regNumber/{regNumber}")
    public ResponseEntity<Set<Service>> getServicesByVehicleRegNumber(@PathVariable(name = "regNumber") String regNumber) {
        if (regNumber == null && !Character.isLetter(regNumber.charAt(0))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Service> services = serviceRepository.findServiceByVehicleRegNumber(regNumber);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Service> createNewServiceRecord(@RequestBody Service service) {
        serviceRepository.save(service);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteServiceRecordById(@PathVariable(name = "id") Long id){
        return null; // TODO write logic for deleting ServiceRecord by id
    }
}
