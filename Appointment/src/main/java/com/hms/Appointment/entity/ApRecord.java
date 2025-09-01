package com.hms.Appointment.entity;

import com.hms.Appointment.dto.ApRecordDto;
import com.hms.Appointment.dto.PrescriptionDto;
import com.hms.Appointment.dto.RecordDetails;
import com.hms.Appointment.utility.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ApRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private String symptoms;
    private String diagnosis;
    private String tests;
    private String notes;
    private String referral;
    private LocalDate followUpDate;
    private LocalDateTime createAt;


    public ApRecordDto toDto(){
        //Long appointmentId = appointment != null ? appointment.getId() : null;
        return new ApRecordDto(id, patientId, doctorId, appointment.getId(),
                StringListConverter.convertStringToList(symptoms),
                diagnosis,
                StringListConverter.convertStringToList(tests),
                notes, referral, null, followUpDate, createAt);
    }
    public RecordDetails toRecordDetails(){
        return new RecordDetails(id, patientId, doctorId, null, appointment.getId(),
                StringListConverter.convertStringToList(symptoms),
                diagnosis,
                StringListConverter.convertStringToList(tests),
                notes, referral, followUpDate, createAt);
    }



}
