package com.hms.Appointment.service;

import com.hms.Appointment.client.ProfileClient;
import com.hms.Appointment.dto.*;
import com.hms.Appointment.entity.Appointment;
import com.hms.Appointment.exception.HmsException;
import com.hms.Appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ApiService apiService;

    @Autowired
    private ProfileClient profileClient;

    @Override
    public Long scheduleAppointment(AppointmentDto appointmentDto) throws HmsException {
//        Boolean doctorExists=apiService.doctorExists(appointmentDto.getDoctorId()).block();
        Boolean doctorExists=profileClient.doctorExists(appointmentDto.getDoctorId());
        if(doctorExists == null || !doctorExists){
            throw new HmsException("DOCTOR_NOT_FOUND");
        }
//        Boolean patientExists=apiService.patientExists(appointmentDto.getPatientId()).block();
        Boolean patientExists=profileClient.patientExists(appointmentDto.getPatientId());
        if(patientExists == null || !patientExists){
            throw new HmsException("PATIENT_NOT_FOUND");
        }
        appointmentDto.setStatus(Status.SHEDULED);
        return appointmentRepository.save(appointmentDto.toEntity()).getId();
    }

    @Override
    public void updateAppointment(Long appointmentId, AppointmentDto appointmentDto) {

    }

    @Override
    public void cancelAppointment(Long appointmentId) throws HmsException {
        Appointment appointment = appointmentRepository
                .findById(appointmentId)
                .orElseThrow(()->new HmsException("APPOINTMENT_NOT_FOUND"));
        if(appointment.getStatus().equals(Status.CANCELLED)){
            throw new HmsException("APPOINTMENT_ALREADY_CANCELLED");
        }
        appointment.setStatus(Status.CANCELLED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void completeAppointment(Long appointmentId) {

    }

    @Override
    public void rescheduleAppointment(Long appointmentId, String newDateTime) {

    }

    @Override
    public  AppointmentDto getAppointmentDetails(Long appointmentId) throws HmsException {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(()->new HmsException("APPOINTMENT_NOT_FOUND")).toDto();
    }

    @Override
    public void confirmAppointment(Long appointmentId) {

    }

    @Override
    public AppointmentDetails getAppointmentDetailsWithName(Long appointmentId) throws HmsException {
        AppointmentDto appointmentDto= appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new HmsException("APPOINTMENT_NOT_FOUND")).toDto();
        //DoctorDto doctorDto=apiService.getDoctorById(appointmentDto.getDoctorId()).block();
        //PatientDto patientDto=apiService.getPatientById(appointmentDto.getPatientId()).block();
        DoctorDto doctorDto=profileClient.getDoctorById(appointmentDto.getDoctorId());
        PatientDto patientDto=profileClient.getPatientById(appointmentDto.getPatientId());

        return new AppointmentDetails(appointmentDto.getId(),
                appointmentDto.getPatientId(),
                patientDto.getName(),
                patientDto.getEmail(),
                patientDto.getPhone(),
                patientDto.getAddress(),
                appointmentDto.getDoctorId(),
                doctorDto.getName(),
                appointmentDto.getAppointmentTime(),
                appointmentDto.getStatus(),
                appointmentDto.getReason(),
                appointmentDto.getNotes());
    }

    @Override
    public List<AppointmentDetails> getAllAppointmentsByPatientId(Long patientId) throws HmsException {
        return appointmentRepository.findAllByPatientId(patientId).stream()
                .map(appointment->{
                    DoctorDto doctorDto=profileClient.getDoctorById(appointment.getDoctorId());
                    appointment.setDoctorName(doctorDto.getName());
                    return appointment;
                }).toList();
    }

    @Override
    public List<AppointmentDetails> getAllAppointmentsByDoctorId(Long doctorId) throws HmsException {
        return appointmentRepository.findAllByDoctorId(doctorId).stream()
                .map(appointment->{
                    PatientDto patientDto=profileClient.getPatientById(appointment.getPatientId());
                    appointment.setPatientName(patientDto.getName());
                    appointment.setPatientEmail(patientDto.getEmail());
                    appointment.setPatientPhone(patientDto.getPhone());
                    return appointment;
                }).toList();
    }
}
