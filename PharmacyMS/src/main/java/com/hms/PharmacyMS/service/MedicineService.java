package com.hms.PharmacyMS.service;

import com.hms.PharmacyMS.dto.MedicineDto;
import com.hms.PharmacyMS.exception.HmsException;

import java.util.List;

public interface MedicineService {
    public Long addMedicine(MedicineDto medicineDto) throws HmsException;
    public MedicineDto getMedicineById(Long id) throws HmsException;
    public void updateMedicine(MedicineDto medicineDto) throws HmsException;
    public void deleteMedicine(Long id) throws HmsException;
    public List<MedicineDto> getAllMedicines() throws HmsException;
}
