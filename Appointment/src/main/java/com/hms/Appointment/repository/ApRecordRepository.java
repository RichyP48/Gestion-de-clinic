package com.hms.Appointment.repository;

import com.hms.Appointment.entity.ApRecord;
import com.hms.Appointment.exception.HmsException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApRecordRepository extends CrudRepository<ApRecord, Long> {
    Optional<ApRecord> findByAppointment_Id(Long appointmentId) ;

    List<ApRecord> findByPatientId(Long patientId);
    Boolean existsByAppointment_Id(Long appointmentId);
}
