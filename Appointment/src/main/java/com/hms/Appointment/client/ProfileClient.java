package com.hms.Appointment.client;

import com.hms.Appointment.config.FeignClientInterceptor;
import com.hms.Appointment.dto.DoctorDto;
import com.hms.Appointment.dto.DoctorName;
import com.hms.Appointment.dto.PatientDto;
import com.hms.Appointment.exception.HmsException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ProfileMS", configuration = FeignClientInterceptor.class)
public interface ProfileClient {
    @GetMapping("/profile/doctor/exists/{id}")
    Boolean doctorExists(@PathVariable("id") Long id);
    @GetMapping("/profile/patient/exists/{id}")
    Boolean patientExists(@PathVariable("id") Long id);
    @GetMapping("/profile/patient/get/{id}")
    PatientDto getPatientById(@PathVariable("id") Long id);
    @GetMapping("/profile/doctor/get/{id}")
    DoctorDto getDoctorById(@PathVariable("id") Long id);

    @GetMapping("/profile/doctor/getDoctorsById")
    List<DoctorName> getDoctorsById(@RequestParam List<Long> ids);

}
