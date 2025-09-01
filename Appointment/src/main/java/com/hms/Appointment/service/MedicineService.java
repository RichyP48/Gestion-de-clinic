package com.hms.Appointment.service;

import com.hms.Appointment.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    public Long saveMedicine(MedicineDto request);

    public List<MedicineDto> saveAllMedicines(List<MedicineDto> requestList);
    public List<MedicineDto> getAllMedicinesByPrescriptionId(Long prescriptionId);

}
