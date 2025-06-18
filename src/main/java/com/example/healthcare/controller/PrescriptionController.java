package com.example.healthcare.controller;

import com.example.healthcare.model.Prescription;
import com.example.healthcare.service.PrescriptionService;
import com.example.healthcare.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final TokenUtil tokenUtil;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, TokenUtil tokenUtil) {
        this.prescriptionService = prescriptionService;
        this.tokenUtil = tokenUtil;
    }

    // ✅ POST endpoint to create prescription with token as @PathVariable
    @PostMapping("/prescriptions/{token}")
    public ResponseEntity<?> createPrescription(
            @Valid @RequestBody Prescription prescription,
            @PathVariable String token) {

        if (!tokenUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        Prescription saved = prescriptionService.savePrescription(prescription);
        return ResponseEntity.ok(saved);
    }

    // ✅ GET endpoint to retrieve all prescriptions for a doctor (by token)
    @GetMapping("/prescriptions/{token}")
    public ResponseEntity<?> getPrescriptionsByDoctor(
            @PathVariable String token) {

        if (!tokenUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByDoctorToken(token);
        return ResponseEntity.ok(prescriptions);
    }
}
