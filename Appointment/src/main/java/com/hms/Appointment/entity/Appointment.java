package com.hms.Appointment.entity;


import com.hms.Appointment.dto.AppointmentDto;
import com.hms.Appointment.dto.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private Status status;
    private  String reason;
    private String notes;



    public AppointmentDto toDto() {
        return new AppointmentDto(id, patientId, doctorId, appointmentTime, status, reason, notes);
    }
    public Appointment(Long id) {
        this.id = id;
    }

}

