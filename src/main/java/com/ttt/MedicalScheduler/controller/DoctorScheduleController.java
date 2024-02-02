package com.ttt.MedicalScheduler.controller;

import com.ttt.MedicalScheduler.model.DoctorSchedule;
import com.ttt.MedicalScheduler.service.DoctorScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/doctor-schedules")
@RequiredArgsConstructor
public class DoctorScheduleController {
    private final DoctorScheduleService doctorScheduleService;

    @GetMapping("/{doctorId}/{scheduleDate}")
    public ResponseEntity<List<DoctorSchedule>> getDoctorSchedule(@PathVariable Long doctor_id, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate scheduleDate) {
        List<DoctorSchedule> doctorSchedule = doctorScheduleService.getDoctorSchedule(doctor_id, scheduleDate);
        return ResponseEntity.ok(doctorSchedule);
    }
}