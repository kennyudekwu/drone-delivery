package com.flutterwavedemo.dronedelivery.web;

import com.flutterwavedemo.dronedelivery.entity.Medication;
import com.flutterwavedemo.dronedelivery.service.MedicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@RestController
@RequestMapping("/medication")
public class MedicationController {
    MedicationService medicationService;

    @PostMapping("/storeMedication")
    public ResponseEntity<Medication> storeMedication(@Valid @RequestParam("name") String name,
                                                      @Valid @RequestParam("weight") Double weight,
                                                      @Valid @RequestParam("code") String code,
                                                      @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(medicationService.saveMedication(file, name, weight, code), HttpStatus.CREATED);
    }

}
