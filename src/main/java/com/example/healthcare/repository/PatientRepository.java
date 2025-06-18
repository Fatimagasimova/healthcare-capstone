package com.example.healthcare.repository;

import com.example.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // E-poçt ilə xəstəni tap (login üçün)
    Optional<Patient> findByEmail(String email);

    // Ad və soyad ilə xəstəni axtar
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);

    // ✅ Əlavə: Email və ya telefon nömrəsinə görə axtarış
    Optional<Patient> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
