package com.flutterwavedemo.dronedelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DroneDeliveryApplication {

	@GetMapping("/demo")
	public String getResponse() {
		return "Hello the app is working (changed again)...";
	}

	public static void main(String[] args) {
		SpringApplication.run(DroneDeliveryApplication.class, args);
	}

}
