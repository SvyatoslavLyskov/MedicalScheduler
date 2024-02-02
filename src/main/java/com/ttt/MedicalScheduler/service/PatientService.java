package com.ttt.MedicalScheduler.service;

import com.ttt.MedicalScheduler.model.Patient;
import com.ttt.MedicalScheduler.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}