package com.hms.Appointment.service;

import com.hms.Appointment.dto.ApRecordDto;
import com.hms.Appointment.dto.RecordDetails;
import com.hms.Appointment.exception.HmsException;

import java.util.List;

public interface ApRecordService {
    public Long createApRecord(ApRecordDto request) throws HmsException;
    public void updateApRecord(ApRecordDto request) throws HmsException;
    public ApRecordDto getApRecordByAppointmentId(Long appointmentId) throws HmsException;
    public ApRecordDto getApRecordDetailsByAppointmentId(Long appointmentId) throws HmsException;
    public ApRecordDto getApRecordById(Long recordId) throws HmsException;
    List<RecordDetails> getRecordsByPatientId(Long patientId) throws HmsException;
    Boolean isRecordExists(Long appointmentId) throws HmsException;
}
