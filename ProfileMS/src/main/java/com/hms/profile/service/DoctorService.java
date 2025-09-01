package com.hms.profile.service;

import com.hms.profile.dto.DoctorDropdown;
import com.hms.profile.dto.DoctorDto;
import com.hms.profile.entity.Doctor;
import com.hms.profile.exception.HmsException;

import java.util.List;

public interface DoctorService {
    public Long addDoctor(DoctorDto doctorDto) throws HmsException;
    public DoctorDto getDoctorById(Long id) throws HmsException;
    public DoctorDto updateDoctor(DoctorDto doctorDto) throws HmsException;
    public Boolean doctorExists(Long id) throws HmsException;
    public List<DoctorDropdown> getDoctorDropdown() throws HmsException;
    public List<DoctorDropdown> getDoctorsById(List<Long> ids) throws HmsException;
}
