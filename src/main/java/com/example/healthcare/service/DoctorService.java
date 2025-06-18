package com.example.healthcare.service;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.model.Doctor;
import com.example.healthcare.repository.AppointmentRepository;
import com.example.healthcare.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ Bu metod həkimin həmin gün üçün boş vaxtlarını qaytarır (bron olunmuş saatlar çıxılır)
    public List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        List<String> availableTimes = new ArrayList<>();

        // 10:00-dan 17:00-a qədər hər saat üçün zaman əlavə et
        for (int hour = 10; hour <= 17; hour++) {
            availableTimes.add(LocalTime.of(hour, 0).toString());
        }

        // Həmin tarix və həkim üçün artıq bron olunmuş saatları tap
        LocalDateTime startOfDay = date.atTime(0, 0);
        LocalDateTime endOfDay = date.atTime(23, 59);

        List<Appointment> appointments = appointmentRepository
                .findByDoctorIdAndAppointmentTimeBetween(doctorId, startOfDay, endOfDay);

        List<LocalTime> bookedTimes = appointments.stream()
                .map(a -> a.getAppointmentTime().toLocalTime())
                .toList();

        // Bron olunmuş saatları siyahıdan çıx
        availableTimes.removeIf(bookedTimes::contains);

        return availableTimes;
    }
}
