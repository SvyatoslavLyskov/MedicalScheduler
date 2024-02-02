package com.ttt.MedicalScheduler.repository;

import com.ttt.MedicalScheduler.model.Doctor;
import com.ttt.MedicalScheduler.model.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    List<DoctorSchedule> findByDoctorIdAndScheduleDate(Long doctorId, LocalDate scheduleDate);
}
