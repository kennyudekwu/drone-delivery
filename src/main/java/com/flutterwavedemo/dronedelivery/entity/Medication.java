package com.flutterwavedemo.dronedelivery.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "medication")
public class Medication {

    @Id // primary key annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    @NonNull
    @Column(name = "code", nullable = false)
    private String code;

    @NonNull
    @Column(name = "image", nullable = false)
    private String image;

}
