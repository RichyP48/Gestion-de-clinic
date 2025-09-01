package com.hms.profile.service;

import com.hms.profile.dto.PatientDto;

import com.hms.profile.exception.HmsException;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientService {
    public Long addPatient(PatientDto patientDto) throws HmsException;
    public PatientDto getPatientById(Long id) throws HmsException;
    public PatientDto updatePatient(PatientDto patientDto) throws HmsException;
    public Boolean patientExists(Long id) throws HmsException;
}
