package com.example.healthcare.controller;

import com.example.healthcare.model.Doctor;
import com.example.healthcare.service.DoctorService;
import com.example.healthcare.util.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final TokenUtil tokenUtil;

    public DoctorController(DoctorService doctorService, TokenUtil tokenUtil) {
        this.doctorService = doctorService;
        this.tokenUtil = tokenUtil;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long id,
            @RequestParam("date") String date,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        if (!tokenUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        List<String> availableTimes = doctorService.getAvailableTimes(id, LocalDate.parse(date));
        return ResponseEntity.ok(availableTimes);
    }
}
