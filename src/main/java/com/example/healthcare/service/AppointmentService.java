package com.example.healthcare.service;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // ✅ Tam tələblərə uyğun yeni metod
    public Appointment bookAppointment(Appointment appointment) {
        // Burada əlavə yoxlamalar da əlavə edilə bilər (məsələn, vaxt uyğunluğu)
        return appointmentRepository.save(appointment);
    }
}
