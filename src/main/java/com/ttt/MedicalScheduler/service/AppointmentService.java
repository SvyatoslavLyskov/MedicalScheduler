package com.ttt.MedicalScheduler.service;

import com.ttt.MedicalScheduler.model.Appointment;
import com.ttt.MedicalScheduler.model.Patient;
import com.ttt.MedicalScheduler.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AppointmentService {

    private static final LocalTime START_WORK_TIME = LocalTime.parse("08:00");
    private static final LocalTime END_WORK_TIME = LocalTime.parse("18:00");

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        log.debug("Getting all appointments");
        return appointmentRepository.findAll();
    }

    public List<LocalDateTime> getPatientAppointments(Long patientId) {
        log.debug("Getting appointments for patient with ID: {}", patientId);
        return appointmentRepository.findSlotTimesByPatientId(patientId);
    }

    public void bookSlot(Long patientId, String slotTime) {
        try {
            log.info("Attempting to book slot for patient with ID: {}, slot time: {}", patientId, slotTime);
            Patient patient = appointmentRepository.getReferenceById(patientId).getPatient();
            LocalDateTime parsedDateTime = parseDateTime(slotTime);
            saveAppointment(patient, parsedDateTime);
            log.info("Slot successfully booked for patient with ID: {}, at time: {}", patientId, parsedDateTime);
        } catch (DateTimeParseException e) {
            log.error("Error parsing date while booking slot: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Error booking slot: {}", e.getMessage());
        }
    }

    public List<String> getAvailableSlots() {
        log.debug("Getting available slots");
        LocalDateTime endDateTime = LocalDateTime.now().withHour(END_WORK_TIME.getHour()).withMinute(END_WORK_TIME.getMinute());
        List<LocalDateTime> availableSlots = appointmentRepository.findAvailableSlots(LocalDateTime.now(), endDateTime);
        return formatSlots(availableSlots);
    }

    public List<Appointment> getAppointmentsByDoctorIdAndDate(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId, start, end);
    }

    private LocalDateTime parseDateTime(String slotTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(slotTime, formatter);
    }

    private void saveAppointment(Patient patient, LocalDateTime appointmentTime) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setAppointmentTime(appointmentTime);
        appointmentRepository.save(appointment);
    }

    private List<String> formatSlots(List<LocalDateTime> slots) {
        List<String> formattedSlots = new ArrayList<>();
        for (LocalDateTime slot : slots) {
            if (isWithinWorkingHours(slot)) {
                formattedSlots.add(formatDateTime(slot));
            }
        }
        return formattedSlots;
    }

    private boolean isWithinWorkingHours(LocalDateTime dateTime) {
        return !dateTime.toLocalTime().isBefore(START_WORK_TIME) && !dateTime.toLocalTime().isAfter(END_WORK_TIME);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}