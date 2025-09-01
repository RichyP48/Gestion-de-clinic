package com.hms.profile.service;

import com.hms.profile.dto.DoctorDropdown;
import com.hms.profile.dto.DoctorDto;
import com.hms.profile.exception.HmsException;
import com.hms.profile.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public Long addDoctor(DoctorDto doctorDto) throws HmsException {
        if(doctorDto.getEmail() !=null && doctorRepository.findByEmail(doctorDto.getEmail()).isPresent()) throw new HmsException("DOCTOR_ALREADY_EXISTS");
        if(doctorDto.getLicenseNo() !=null && doctorRepository.findByLicenseNo(doctorDto.getLicenseNo()).isPresent()) throw new HmsException("DOCTOR_ALREADY_EXISTS");

        return doctorRepository.save(doctorDto.toEntity()).getId();
    }

    @Override
    public DoctorDto getDoctorById(Long id) throws HmsException {
        System.out.println("Fetching doctor with ID: " + id);

        return doctorRepository.findById(id).orElseThrow(()->new HmsException("DOCTOR_NOT_FOUND")).toDto();

    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto) throws HmsException {

        doctorRepository.findById(doctorDto.getId()).orElseThrow(()->new HmsException("DOCTOR_NOT_FOUND"));
        return doctorRepository.save(doctorDto.toEntity()).toDto();
    }

    @Override
    public Boolean doctorExists(Long id) throws HmsException {
        return doctorRepository.existsById(id);
    }

    @Override
    public List<DoctorDropdown> getDoctorDropdown() throws HmsException {
        return doctorRepository.findAllDoctorDropdown();
    }


    public List<DoctorDropdown> getDoctorsById(List<Long> ids) throws HmsException {
        return doctorRepository.findAllDoctorDropdownByIds(ids);
    }
}
