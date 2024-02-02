package com.ttt.MedicalScheduler.repository;

import com.ttt.MedicalScheduler.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
