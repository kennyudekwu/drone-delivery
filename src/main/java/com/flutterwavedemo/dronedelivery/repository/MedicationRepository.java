package com.flutterwavedemo.dronedelivery.repository;

import com.flutterwavedemo.dronedelivery.entity.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, Long> {
    Medication findByCode(String code);
}