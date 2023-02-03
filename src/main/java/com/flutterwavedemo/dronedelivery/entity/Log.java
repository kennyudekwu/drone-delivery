package com.flutterwavedemo.dronedelivery.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "log")
public class Log {
    /*
        Stores snapshot of drone's battery at certain time instances
    */
    @Id // primary key annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_stamp", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timeStamp = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "drone_serial_number", referencedColumnName = "serial_number") // specifying id to reference as foreign key
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Drone drone;

    @Column(name = "drone_battery", nullable = false) // will be set during battery drain procedures
    private Double droneBattery;

}
