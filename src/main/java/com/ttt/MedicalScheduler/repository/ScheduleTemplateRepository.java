package com.ttt.MedicalScheduler.repository;

import com.ttt.MedicalScheduler.model.ScheduleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleTemplateRepository extends JpaRepository<ScheduleTemplate, Long> {
    List<ScheduleTemplate> findByDoctorId(Long doctor_id);
}
