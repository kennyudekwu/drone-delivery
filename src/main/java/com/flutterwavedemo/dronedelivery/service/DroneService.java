package com.flutterwavedemo.dronedelivery.service;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.entity.Medication;

import java.util.List;

public interface DroneService {
    List<Drone> checkLoadableDrones();
    Double checkDroneBattery(String serialNumber);
    Drone registerDrone(Drone drone);
    List<Medication> showLoadedMedications(String serialNumber);

    void loadDrone(String code, String serialNumber) throws InterruptedException;
}