package com.project.patientsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ReponsePatientDto {
    private String message;
    private  PatientDto patient;
    private List<PatientDto> patients;
}
