package com.hms.Appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetails {
    private Long id;
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientPhone;
    private String patientAddress;
    private Long doctorId;
    private String doctorName;
    private LocalDateTime appointmentTime;
    private Status status;
    private  String reason;
    private String notes;

}
