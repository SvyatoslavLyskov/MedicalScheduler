package com.ttt.MedicalScheduler.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookSlotRequest {
    private Long patientId;
    private String slotTime;
}
