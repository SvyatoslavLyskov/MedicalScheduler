package com.ttt.MedicalScheduler.repository;

import com.ttt.MedicalScheduler.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
