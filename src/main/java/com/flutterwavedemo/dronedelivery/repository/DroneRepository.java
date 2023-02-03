package com.flutterwavedemo.dronedelivery.repository;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.entity.Medication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DroneRepository extends CrudRepository<Drone, Long> {
    // only return drones in their "IDLE" states
    @Query("SELECT drone FROM Drone drone WHERE drone.state = 'IDLE'")
    List<Drone> findLoadableDrones();

    // the above query could be rewritten as List<Drone> findByState(String state) - the state 'IDLE' will be passed in

    // override method by running query to find the drone with the specific serial number
    Drone findBySerialNumber(String serialNumber);
}