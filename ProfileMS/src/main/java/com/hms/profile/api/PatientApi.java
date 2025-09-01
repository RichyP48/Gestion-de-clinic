package com.hms.profile.api;

import com.hms.profile.dto.PatientDto;
import com.hms.profile.exception.HmsException;
import com.hms.profile.service.DoctorService;
import com.hms.profile.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile/patient")
@Validated
public class PatientApi {
    @Autowired
    private PatientService patientService;


    @PostMapping("/add")
    public ResponseEntity<Long> addPatient(@RequestBody PatientDto patientDto) throws HmsException {
        return new ResponseEntity<>(patientService.addPatient(patientDto), HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody @Valid PatientDto patientDto) throws HmsException {
        return new ResponseEntity<>(patientService.updatePatient(patientDto), HttpStatus.OK);
    }
    //utilisation pour appointment
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> patientExists(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(patientService.patientExists(id), HttpStatus.OK);
    }
}
