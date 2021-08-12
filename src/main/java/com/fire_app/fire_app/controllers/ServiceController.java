package com.fire_app.fire_app.controllers;

import com.fire_app.fire_app.domain.model.Service;
import com.fire_app.fire_app.service.ServiceModelService;
import com.fire_app.fire_app.util.ErrorMessage;
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

    private final ServiceModelService serviceModelService;


    @Autowired
    public ServiceController(ServiceModelService serviceModelService) {
        this.serviceModelService = serviceModelService;
    }

    @GetMapping
    public ResponseEntity<List<Service>> findAll() {
        return new ResponseEntity<>(serviceModelService.getAllServiceRecords(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findServiceById(@PathVariable Long id) {
       Optional<Service> optionalService = serviceModelService.getServiceRecordById(id);
       if(optionalService.isPresent()){
           return new ResponseEntity<>(optionalService.get(),HttpStatus.OK);
       }else {
           return new ResponseEntity<>(new ErrorMessage("Service record with id: " + id + " not found!"), HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/byVehicleId/{id}")
    public ResponseEntity<?> findServiceByVehicleId(@PathVariable Long id) {
       Optional<Set<Service>> services = Optional.ofNullable(serviceModelService.getServicesByVehicleId(id));
       if(services.isPresent()){
           return new ResponseEntity<>(services,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(new ErrorMessage("No vehicle with id: " + id + " found!"), HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/byVehicleRegNumber/{regNumber}")
    public ResponseEntity<?> findServiceByVehicleRegNumber(@PathVariable String regNumber) {
       Optional<Set<Service>> services = Optional.ofNullable(serviceModelService.getServicesByVehicleRegNumber(regNumber));
       if(services.isPresent()){
           return new ResponseEntity<>(services,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(new ErrorMessage("No vehicle with register number: " + regNumber + " found!"), HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping
    public ResponseEntity<?> createServiceRecord(@RequestBody Service service) {
        Optional<ErrorMessage> optionalErrorMessage = serviceModelService.createServiceRecord(service);
        if(optionalErrorMessage.isPresent()){
            return new ResponseEntity<>(optionalErrorMessage.get(),HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(service.getId(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceRecord(@PathVariable Long id){
        Optional<ErrorMessage> optionalErrorMessage = serviceModelService.deleteService(id);
        if(optionalErrorMessage.isPresent()){
            return new ResponseEntity<>(optionalErrorMessage.get(),HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
