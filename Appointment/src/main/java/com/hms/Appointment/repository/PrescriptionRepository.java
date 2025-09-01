package com.hms.Appointment.repository;

import com.hms.Appointment.entity.Prescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    Optional<Prescription> findByAppointment_Id(Long prescriptionId);
    List<Prescription> findAllByPatientId(Long patientId);
}
