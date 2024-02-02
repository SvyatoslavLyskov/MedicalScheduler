package com.ttt.MedicalScheduler.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "schedule_template")
public class ScheduleTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ElementCollection
    @CollectionTable(name = "work_days", joinColumns = @JoinColumn(name = "template_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> workDays;

    private LocalTime startTime;

    private LocalTime endTime;
}
