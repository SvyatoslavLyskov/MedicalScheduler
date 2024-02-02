package com.ttt.MedicalScheduler.repository;

import com.ttt.MedicalScheduler.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorIdAndAppointmentTimeBetween(Long doctor_id, LocalDateTime start, LocalDateTime end);

    @Query("SELECT appointmentTime FROM Appointment WHERE patient.id = ?1")
    List<LocalDateTime> findSlotTimesByPatientId(Long patient_id);

    @Query("SELECT DISTINCT a.appointmentTime FROM Appointment a " +
            "WHERE a.appointmentTime BETWEEN :startDateTime AND :endDateTime")
    List<LocalDateTime> findAvailableSlots(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}