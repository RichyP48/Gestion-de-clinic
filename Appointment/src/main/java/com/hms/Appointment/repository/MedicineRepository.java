package com.hms.Appointment.repository;

import com.hms.Appointment.entity.Medicine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {
    List<Medicine> findAllByPrescription_Id(Long prescriptionId);
}
