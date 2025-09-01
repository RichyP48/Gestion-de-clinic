package com.hms.profile.api;

import com.hms.profile.dto.DoctorDropdown;
import com.hms.profile.dto.DoctorDto;
import com.hms.profile.dto.PatientDto;
import com.hms.profile.exception.HmsException;
import com.hms.profile.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/profile/doctor")
@Validated
public class DoctorApi {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<Long> addDoctor(@RequestBody DoctorDto doctorDto) throws HmsException {
        return new ResponseEntity<>(doctorService.addDoctor(doctorDto), HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.CREATED);
    }
//    @PutMapping("/update")
//    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody  DoctorDto doctorDto) throws HmsException {
//        return new ResponseEntity<>(doctorService.updateDoctor(doctorDto), HttpStatus.OK);
//    }
    @PutMapping("/update")
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody @Valid DoctorDto doctorDto) throws HmsException {
        return new ResponseEntity<>(doctorService.updateDoctor(doctorDto), HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> doctorExists(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(doctorService.doctorExists(id), HttpStatus.OK);
    }
    @GetMapping("/dropdowns")
    public ResponseEntity<List<DoctorDropdown>> getDoctorDropdowns() throws HmsException {
        return new ResponseEntity<>(doctorService.getDoctorDropdown(), HttpStatus.OK);
    }
    @GetMapping("/getDoctorsById")
    public ResponseEntity<List<DoctorDropdown>> getDoctorsById(@RequestParam List<Long> ids) throws HmsException {
        return new ResponseEntity<>(doctorService.getDoctorsById(ids), HttpStatus.OK);
    }

}
