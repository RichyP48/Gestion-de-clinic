package com.hms.Appointment.dto;

import com.hms.Appointment.entity.ApRecord;
import com.hms.Appointment.entity.Appointment;
import com.hms.Appointment.utility.StringListConverter;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApRecordDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private List<String> symptoms;
    private String diagnosis;
    private List<String> tests;
    private String notes;
    private String referral;
    private PrescriptionDto prescription;
    private LocalDate followUpDate;
    private LocalDateTime createAt;




    public ApRecord toEntity(){
       return new ApRecord(id, patientId, doctorId, new Appointment(appointmentId),
               StringListConverter.convertListToString(symptoms),
               diagnosis, StringListConverter.convertListToString(tests),
               notes, referral, followUpDate, createAt);
    }
}
