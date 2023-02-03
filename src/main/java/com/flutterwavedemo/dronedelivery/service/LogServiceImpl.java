package com.flutterwavedemo.dronedelivery.service;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.entity.Log;
import com.flutterwavedemo.dronedelivery.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LogServiceImpl implements LogService {
    LogRepository logRepository;

    @Override
    public Log saveLog(Log log, Drone drone) {
        log.setDrone(drone);
        log.setDroneBattery(drone.getBatteryCapacity());
        return logRepository.save(log);
    };

}
