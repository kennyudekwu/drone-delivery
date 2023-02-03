package com.flutterwavedemo.dronedelivery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flutterwavedemo.dronedelivery.customconstraints.DroneModelConstraint;
import com.flutterwavedemo.dronedelivery.customconstraints.DroneStateConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "drone")
public class Drone {
    // public static Double droneWeightLimit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate primary key for each record
    @Column(name = "id", nullable = false) // naming the columns and stating that the column cannot be null
    private Long id;

    @NonNull
    @Column(name = "serial_number", nullable = false)
    @NotBlank(message = "Serial Number cannot be blank")
    @Size(min=3, max=100)
    private String serialNumber;

    @NonNull
    @Column(name = "model", nullable = false)
    @NotBlank(message = "Model cannot be blank")
    @DroneModelConstraint(message = "Model must be available")
    private String model;

    @NonNull
    @Column(name = "weight_limit", nullable = false)
    @NotBlank(message = "Weight Limit cannot be blank")
    @Max(500)
    private Double weightLimit;

    // drone's can be registered with a battery level less than 100%
    @NonNull
    @Column(name = "battery_capacity", nullable = false)
    @NotBlank(message = "Battery Capacity cannot be blank")
    @Max(100)
    private Double batteryCapacity;

    @NonNull
    @Column(name = "state", nullable = false)
    @NotBlank(message = "State cannot be blank")
    @DroneStateConstraint(message = "State must be available")
    private String state;

    // no need for modelling relationship from drone to 'Medication' because we wouldn't need to perform any cascading
    // operation of related 'Medication' objects
    @Column(name = "medications", nullable = false)
    private List<Medication> medications = new ArrayList<>();

    // load medication unto drone
    public void addMedications(Medication medication) {
        this.medications.add(medication);
    };

    // offload and reset drone weight medications from drone upon delivery
    public void offloadMedications() {
        this.medications = new ArrayList<>();
    };
}
