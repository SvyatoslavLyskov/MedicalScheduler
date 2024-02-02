package com.ttt.MedicalScheduler.service;

import com.ttt.MedicalScheduler.model.DoctorSchedule;
import com.ttt.MedicalScheduler.repository.DoctorScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorScheduleService {
    private final DoctorScheduleRepository doctorScheduleRepository;

    public List<DoctorSchedule> getDoctorSchedule(Long doctorId, LocalDate scheduleDate) {
        return doctorScheduleRepository.findByDoctorIdAndScheduleDate(doctorId, scheduleDate);
    }
}