package com.example.healthcare.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;

    private Long patientId;

    private String medication;

    private String dosage;

    private LocalDate date;

    // Getters və Setters
}
