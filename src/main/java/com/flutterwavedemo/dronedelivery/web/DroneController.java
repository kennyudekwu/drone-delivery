package com.flutterwavedemo.dronedelivery.web;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.entity.Medication;
import com.flutterwavedemo.dronedelivery.service.DroneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/drone")
public class DroneController {
    DroneService droneService;

    @PostMapping("/load-drone/{droneSerialNumber}/medication/{medicationCode}")
    public ResponseEntity<HttpStatus> loadDrone(@PathVariable String droneSerialNumber,
                                                @PathVariable String medicationCode) throws InterruptedException {
        droneService.loadDrone(medicationCode, droneSerialNumber);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/register-drone")
    public ResponseEntity<Drone> registerDrone(@Valid @RequestBody Drone drone) {
        return new ResponseEntity<>(droneService.registerDrone(drone), HttpStatus.CREATED);
    }

    @GetMapping("/check-drone-battery/{droneSerialNumber}")
    public ResponseEntity<Double> getDroneBattery(@PathVariable String droneSerialNumber) {
        return new ResponseEntity<>(droneService.checkDroneBattery(droneSerialNumber), HttpStatus.OK);
    }

    @GetMapping("/check-loadable-drones")
    public ResponseEntity<List<Drone>> getLoadableDrones() {
        return new ResponseEntity<>(droneService.checkLoadableDrones(), HttpStatus.OK);
    }

    @GetMapping("/check-loaded-medications/{droneSerialNumber}")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable String droneSerialNumber) {
        return new ResponseEntity<>(droneService.showLoadedMedications(droneSerialNumber), HttpStatus.OK);
    }


}
