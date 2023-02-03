package com.flutterwavedemo.dronedelivery.repository;

import com.flutterwavedemo.dronedelivery.entity.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
}