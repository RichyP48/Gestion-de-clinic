package com.hms.Appointment.service;

import com.hms.Appointment.dto.AppointmentDetails;
import com.hms.Appointment.dto.AppointmentDto;
import com.hms.Appointment.exception.HmsException;

import java.util.List;

public interface AppointmentService {
    Long scheduleAppointment(AppointmentDto appointmentDto) throws HmsException;
    void updateAppointment(Long appointmentId, AppointmentDto appointmentDto);
    void cancelAppointment(Long appointmentId) throws HmsException;
    void completeAppointment(Long appointmentId);
    void rescheduleAppointment(Long appointmentId, String newDateTime);
    AppointmentDto getAppointmentDetails(Long appointmentId) throws HmsException;
    void confirmAppointment(Long appointmentId);
    AppointmentDetails getAppointmentDetailsWithName(Long appointmentId) throws HmsException;
    List<AppointmentDetails> getAllAppointmentsByPatientId(Long patientId) throws HmsException;
    List<AppointmentDetails> getAllAppointmentsByDoctorId(Long doctorId) throws HmsException;

}
