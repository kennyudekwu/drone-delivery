package com.flutterwavedemo.dronedelivery;

import com.flutterwavedemo.dronedelivery.entity.Drone;
import com.flutterwavedemo.dronedelivery.repository.DroneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.flutterwavedemo.dronedelivery.entity.Medication;
import com.flutterwavedemo.dronedelivery.repository.MedicationRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor // takes care of dependency injection
@SpringBootApplication
public class DroneDeliveryApplication implements CommandLineRunner {
	DroneRepository droneRepository;
	MedicationRepository medicationRepository;

	public static void main(String[] args) {
		SpringApplication.run(DroneDeliveryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Drone[] drones = new Drone[]{
				new Drone("abc", "Lightweight", 120.00, 100.00, "IDLE"),
				new Drone("kdb", "Heavyweight", 400.00, 27.00, "IDLE"),
				new Drone("ikl", "Middleweight", 320.00, 50.00, "IDLE"),
				new Drone("mnp", "Cruiseweight", 200.00, 78.00, "IDLE")
		};

		for (int i = 0; i < drones.length; i++) {
			droneRepository.save(drones[i]);
		}

		Medication[] medications = new Medication[]{
				new Medication("Paracetamol", 10.00, "PCM", "ImageString1"),
				new Medication("Amatem", 40.00, "AMT", "ImageString2"),
				new Medication("Procold", 19.00, "PRC", "ImageString3"),
				new Medication("Aspirin", 60.00, "ASP", "ImageString4")
		};

		for (int i = 0; i < medications.length; i++) {
			medicationRepository.save(medications[i]);
		}
	}
}
