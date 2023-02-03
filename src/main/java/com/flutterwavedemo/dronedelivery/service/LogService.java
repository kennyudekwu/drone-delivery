package com.flutterwavedemo.dronedelivery.service;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.entity.Log;

public interface LogService {
    Log saveLog(Log log, Drone drone); // storing log
}
