package com.example.healthcare.service;

import com.example.healthcare.model.Doctor;
import com.example.healthcare.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ Yeni metod: Müəyyən tarix üçün həkimin boş saatlarını qaytarır
    public List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        List<String> availableTimes = new ArrayList<>();

        // Sadə nümunə saatları (10:00 - 17:00 arası hər saat üçün boşluq)
        for (int hour = 10; hour <= 17; hour++) {
            LocalTime time = LocalTime.of(hour, 0);
            availableTimes.add(time.toString());
        }

        // Burada istəsən "appointmentRepository"-ni əlavə edib artıq bron olunmuş saatları çıxara bilərsən.
        // Bu, demo versiyadır və sən bu hissəni daha da inkişaf etdirə bilərsən.

        return availableTimes;
    }
}
