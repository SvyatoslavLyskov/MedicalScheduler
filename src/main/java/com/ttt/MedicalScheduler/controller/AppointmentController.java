package com.ttt.MedicalScheduler.controller;

import com.ttt.MedicalScheduler.dto.BookSlotRequest;
import com.ttt.MedicalScheduler.model.Appointment;
import com.ttt.MedicalScheduler.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<String>> getAvailableSlots() {
        List<String> availableSlots = appointmentService.getAvailableSlots();
        return new ResponseEntity<>(availableSlots, HttpStatus.OK);
    }

    @PostMapping("/book-slot")
    public ResponseEntity<String> bookSlot(@RequestBody BookSlotRequest request) {
        try {
            appointmentService.bookSlot(request.getPatientId(), request.getSlotTime());
            return new ResponseEntity<>("Slot booked successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<LocalDateTime>> getPatientAppointments(@PathVariable Long patientId) {
        List<LocalDateTime> patientAppointments = appointmentService.getPatientAppointments(patientId);
        return new ResponseEntity<>(patientAppointments, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorIdAndDate(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorIdAndDate(doctorId, start, end);
        return ResponseEntity.ok(appointments);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}