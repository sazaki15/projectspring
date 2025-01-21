package com.project.patientsservice.services;

import com.project.patientsservice.dto.PatientDto;
import com.project.patientsservice.dto.ReponsePatientDto;
import com.project.patientsservice.entities.Patient;
import com.project.patientsservice.repositories.PatientRepositories;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {
    private final PatientRepositories patientRepositories;
    private final ModelMapper modelMapper;

    public PatientServiceImp(PatientRepositories patientRepositories, ModelMapper modelMapper) {
        this.patientRepositories = patientRepositories;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReponsePatientDto getPatients() {
        List<Patient> patients = patientRepositories.findAll();
        List<PatientDto> patientDto = modelMapper.map(patients, new TypeToken<List<PatientDto>>(){}.getType());
        return ReponsePatientDto.builder()
                .patients(patientDto)
                .build();
    }

    @Override
    public ReponsePatientDto getPatientById(Long id) {
        Patient patient = patientRepositories.findById(id).orElseThrow(()->new NotFoundException("Patient not found"));
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        return ReponsePatientDto.builder()
                .patient(patientDto)
                .build();
    }

    @Override
    public ReponsePatientDto addPatient(PatientDto patientDto) {
        Patient newPatient = modelMapper.map(patientDto, Patient.class);
      patientRepositories.save(newPatient);
      return ReponsePatientDto.builder()
              .message("Patient added successfully")
              .build();
    }

    @Override
    public ReponsePatientDto updatePatient(PatientDto patient) {
        return null;
    }

    @Override
    public ReponsePatientDto deletePatient(Long id) {
        return null;
    }
}
