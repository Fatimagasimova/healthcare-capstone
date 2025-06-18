package com.example.healthcare.repository;

import com.example.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // İstəyə uyğun əlavə metodlar:

    // E-poçt ilə xəstəni tap (login üçün istifadə oluna bilər)
    Optional<Patient> findByEmail(String email);

    // Ad və soyad ilə xəstəni axtar
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
