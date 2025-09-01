package com.hms.profile.repository;

import com.hms.profile.dto.PatientDto;
import com.hms.profile.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public  interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByAadharNo(String aadharNo);
}
