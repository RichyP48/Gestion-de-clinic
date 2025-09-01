package com.hms.profile.service;

import com.hms.profile.dto.PatientDto;
import com.hms.profile.entity.Patient;
import com.hms.profile.exception.HmsException;
import com.hms.profile.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Long addPatient(PatientDto patientDto) throws HmsException {
        if(patientDto.getEmail() != null && patientRepository
                .findByEmail(patientDto
                        .getEmail()).isPresent()) throw new HmsException("PATIENT_ALREADY_EXISTS");
        if(patientDto.getAadharNo() !=null && patientRepository
                .findByAadharNo(patientDto
                        .getAadharNo()).isPresent()) throw new HmsException("PATIENT_ALREADY_EXISTS");

        return patientRepository.save(patientDto.toEntity()).getId();
    }

    @Override
    public PatientDto getPatientById(Long id) throws HmsException {
        System.out.println("Fetching patient with ID: " + id);
        return patientRepository.findById(id).orElseThrow(()->new HmsException("PATIENT_NOT_FOUND")).toDto();
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) throws HmsException {
//        if(patientRepository
//                .findById(patientDto.getId())
//                .isPresent()){
//            throw new HmsException("PATIENT_NOT_FOUND");
//        }

        patientRepository.findById(patientDto.getId())
                .orElseThrow(()-> new HmsException("PATIENT_NOT_FOUND"));
        return patientRepository.save(patientDto.toEntity()).toDto();
    }

    @Override
    public Boolean patientExists(Long id) throws HmsException {
        return patientRepository.existsById(id);
    }
}
