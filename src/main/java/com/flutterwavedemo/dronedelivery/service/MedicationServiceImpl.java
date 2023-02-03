package com.flutterwavedemo.dronedelivery.service;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.entity.Medication;
import com.flutterwavedemo.dronedelivery.exception.DroneNotFoundException;
import com.flutterwavedemo.dronedelivery.exception.FileNotValidException;
import com.flutterwavedemo.dronedelivery.exception.MedicationNotFoundException;
import com.flutterwavedemo.dronedelivery.repository.MedicationRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor  // instead of using Autowired, lombok would automatically inject the dependency using
@Service             // constructor created for fields - gradeRepository and studentRepository
public class MedicationServiceImpl implements MedicationService {
    MedicationRepository medicationRepository;

    @Override
    public Medication saveMedication(MultipartFile file, String name, Double weight, String code) {
        Medication medication = new Medication();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            // throw an unchecked exception that will be accounted for in my "exceptions" directory
            // not a valid file exception should be thrown here
            throw new FileNotValidException();
        }
        medication.setCode(code);
        medication.setName(name);
        medication.setWeight(weight);
        try {
            medication.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return medicationRepository.save(medication);
    }

    @Override
    public Medication findMedicationByCode(String medicationCode) {

        Optional<Medication> medication = Optional.ofNullable(medicationRepository.findByCode(medicationCode)); // check it's available

        // unchecked exception handling
        if (medication.isPresent()) {
            return medication.get();
        } else {
            throw new MedicationNotFoundException(medicationCode);
        }
    }

}
