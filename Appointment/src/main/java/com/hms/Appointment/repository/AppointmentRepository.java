package com.hms.Appointment.repository;

import com.hms.Appointment.dto.AppointmentDetails;
import com.hms.Appointment.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    @Query("SELECT new com.hms.Appointment.dto.AppointmentDetails(a.id, a.patientId, null, null, null, null, a.doctorId, null, a.appointmentTime, a.status, a.reason, a.notes) FROM Appointment a WHERE a.patientId = ?1")
    List<AppointmentDetails> findAllByPatientId(Long patientId);

    @Query("SELECT new com.hms.Appointment.dto.AppointmentDetails(a.id, a.patientId, null, null, null, null, a.doctorId, null, a.appointmentTime, a.status, a.reason, a.notes) FROM Appointment a WHERE a.doctorId = ?1")
    List<AppointmentDetails> findAllByDoctorId(Long doctorId);

}
