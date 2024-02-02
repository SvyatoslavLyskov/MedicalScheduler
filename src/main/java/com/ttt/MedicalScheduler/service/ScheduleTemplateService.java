package com.ttt.MedicalScheduler.service;

import com.ttt.MedicalScheduler.model.ScheduleTemplate;
import com.ttt.MedicalScheduler.repository.ScheduleTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleTemplateService {
    private final ScheduleTemplateRepository scheduleTemplateRepository;

    public List<ScheduleTemplate> getTemplatesByDoctorId(Long doctorId) {
        return scheduleTemplateRepository.findByDoctorId(doctorId);
    }

    public ScheduleTemplate createScheduleTemplate(ScheduleTemplate scheduleTemplate) {
        return scheduleTemplateRepository.save(scheduleTemplate);
    }
}
