package com.flutterwavedemo.dronedelivery.service;

import com.flutterwavedemo.dronedelivery.entity.Medication;
import org.springframework.web.multipart.MultipartFile;

public interface MedicationService {
    Medication saveMedication(MultipartFile file, String name, Double weight, String code); // storing medication item in the db
    Medication findMedicationByCode(String medicationCode);
}
