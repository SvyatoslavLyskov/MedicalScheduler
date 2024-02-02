package com.ttt.MedicalScheduler.controller;

import com.ttt.MedicalScheduler.model.ScheduleTemplate;
import com.ttt.MedicalScheduler.service.ScheduleTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-templates")
@RequiredArgsConstructor
public class ScheduleTemplateController {
    private final ScheduleTemplateService scheduleTemplateService;

    @GetMapping("/{doctorId}")
    public ResponseEntity<List<ScheduleTemplate>> getTemplatesByDoctorId(@PathVariable Long doctorId) {
        List<ScheduleTemplate> templates = scheduleTemplateService.getTemplatesByDoctorId(doctorId);
        return ResponseEntity.ok(templates);
    }

    @PostMapping("/create")
    public ResponseEntity<ScheduleTemplate> createScheduleTemplate(@RequestBody ScheduleTemplate scheduleTemplate) {
        ScheduleTemplate createdScheduleTemplate = scheduleTemplateService.createScheduleTemplate(scheduleTemplate);
        return new ResponseEntity<>(createdScheduleTemplate, HttpStatus.CREATED);
    }
}