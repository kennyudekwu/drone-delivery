package com.flutterwavedemo.dronedelivery.service;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.entity.Log;
import com.flutterwavedemo.dronedelivery.entity.Medication;
import com.flutterwavedemo.dronedelivery.exception.DroneNotFoundException;
import com.flutterwavedemo.dronedelivery.exception.DroneNotLoadableException;
import com.flutterwavedemo.dronedelivery.repository.DroneRepository;
import com.flutterwavedemo.dronedelivery.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {
    DroneRepository droneRepository;

    MedicationService medicationService; // inject service to access findByMedicationCode method

    LogService logService;

    //    @Value("${drone.weight.capacity}")
    //    public Double weightLimit;

    @Value("${battery.drain.rate.delivering}")
    public Double deliveringLossRate;

    @Value("${battery.drain.rate.returning}")
    public Double returningLossRate;

    @Override
    public List<Medication> showLoadedMedications(String droneSerialNumber) {
        return findDroneBySerialNumber(droneSerialNumber).getMedications(); // return list of medications on drone
    }

    @Override
    public Double checkDroneBattery(String droneSerialNumber) {
        return findDroneBySerialNumber(droneSerialNumber).getBatteryCapacity();
    }

    @Override
    public List<Drone> checkLoadableDrones() {
        return droneRepository.findLoadableDrones();
    }

    @Override
    public Drone registerDrone(Drone drone) {
        // check that the drone doesn't already exist in the db
        return droneRepository.save(drone);
    }

    @Override
    public void loadDrone(String medicationCode, String droneSerialNumber) throws InterruptedException {
        // check that the drone exist and that the medication exist as well
        // check that drone is in "IDLE" state first
        Drone drone = findDroneBySerialNumber(droneSerialNumber);
        Medication medication = medicationService.findMedicationByCode(medicationCode);


        Double medicationWeight = medication.getWeight();
        Double droneWeightLimit = drone.getWeightLimit(); // initial snapshot of drone's max carrying capacity
        Double droneBatteryLevel = drone.getBatteryCapacity();

        boolean loadable = droneBatteryLevel >= 25 && droneWeightLimit >= medicationWeight;
        if (loadable) {
            drone.setState("LOADING");
            drone.addMedications(medication);
            drone.setWeightLimit(droneWeightLimit - medicationWeight);
            droneRepository.save(drone);
            Thread.sleep(2000); // simulating loading procedure
            drone.setState("LOADED");
            droneRepository.save(drone);

            drone.setState("DELIVERING");
            droneRepository.save(drone);

            batteryDropDelivering(drone, droneWeightLimit); // drone en-route to destination - 5 seconds travel time
            drone.setState("DELIVERED");
            droneRepository.save(drone);
            drone.setWeightLimit(droneWeightLimit); // simulate quick offload procedure
            drone.offloadMedications(); // re-initialize List<Medication> to be empty

            drone.setState("RETURNING");
            droneRepository.save(drone);

            batteryDropReturning(drone);
            drone.setState("IDLE");
            drone.setBatteryCapacity(droneBatteryLevel); // instant battery change simulation for the drone

            droneRepository.save(drone);
        } else {
            // throw not loadable exception
            throw new DroneNotLoadableException(droneSerialNumber, medicationCode);
        }

        // check weight before loading drone - pull out medication from the db first and check its weight against the
        // drone's
        // check battery level to see if it's >= 25 - for state change to "LOADING"
        // change state IDLE -> LOADING (sleep thread first) -> DELIVERING
        // initiate battery drop procedure - create log - store drone id, serial number and battery to "log" table
        // change state to "DELIVERED"
        // change state to "RETURNING"
        // initiate battery drop procedure for return journey
        // change state back to "IDLE"

        // battery charging procedure was not specified in req. docs and as thus it's not implemented

    }


    private void batteryDropDelivering(Drone drone, Double droneMaxLimit) throws InterruptedException {
        // calculating battery level decline rate based on current drone weight
        Double declineRate = ((droneMaxLimit - drone.getWeightLimit())/droneMaxLimit) * deliveringLossRate;

        // simulating 5 seconds travel time
        for (int i = 0; i < 5; i++) {
            // update the Drone and Log tables with drone's current battery level
            drone.setBatteryCapacity(drone.getBatteryCapacity()-declineRate);

            // instantiate log to be saved each second
            Log droneLog = new Log();

            droneRepository.save(drone);
            logService.saveLog(droneLog, drone); // persists log for specific drone
            Thread.sleep(1000);
        }

    }

    private void batteryDropReturning(Drone drone) throws InterruptedException {
        // calculating battery level decline rate based on current drone weight
        Double declineRate = returningLossRate;

        // simulating 5 seconds travel time
        for (int i = 0; i < 5; i++) {
            // update the Drone and Log tables with drone's current battery level
            drone.setBatteryCapacity(drone.getBatteryCapacity()-declineRate);

            // instantiate log to be saved each second
            Log droneLog = new Log();

            droneRepository.save(drone);
            logService.saveLog(droneLog, drone); // persists log for specific drone
            Thread.sleep(1000);
        }

    }

    private Drone findDroneBySerialNumber(String droneSerialNumber) {

        Optional<Drone> drone = Optional.ofNullable(droneRepository.findBySerialNumber(droneSerialNumber)); // check it's available

        // unchecked exception handling
        if (drone.isPresent()) {
            return drone.get();
        } else {
            throw new DroneNotFoundException(droneSerialNumber);
        }
    }

}