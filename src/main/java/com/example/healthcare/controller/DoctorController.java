package com.example.healthcare.controller;

import com.example.healthcare.model.Doctor;
import com.example.healthcare.service.DoctorService;
import com.example.healthcare.util.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DoctorController {

    private final DoctorService doctorService;
    private final TokenUtil tokenUtil;

    public DoctorController(DoctorService doctorService, TokenUtil tokenUtil) {
        this.doctorService = doctorService;
        this.tokenUtil = tokenUtil;
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/{role}/doctor/{doctorId}/availability/{date}/{token}")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable String role,
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token
    ) {
        if (!tokenUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        List<String> availableTimes = doctorService.getAvailableTimes(doctorId, LocalDate.parse(date));

        Map<String, Object> response = new HashMap<>();
        response.put("doctorId", doctorId);
        response.put("date", date);
        response.put("availableTimes", availableTimes);

        return ResponseEntity.ok(response);
    }
}
