package com.hms.Appointment.service;

import com.hms.Appointment.dto.PrescriptionDetails;
import com.hms.Appointment.dto.PrescriptionDto;
import com.hms.Appointment.exception.HmsException;

import java.util.List;

public interface PrescriptionService {
    public Long savePrescription(PrescriptionDto request);
    public PrescriptionDto getPrescriptionByAppointmentId(Long appointmentId) throws HmsException;
    public PrescriptionDto getPrescriptionById(Long prescriptionId) throws HmsException;
    public List<PrescriptionDetails> getPrescriptionsByPatientId(Long patientId) throws HmsException;
}
