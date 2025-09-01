package com.hms.Appointment.service;

import com.hms.Appointment.dto.MedicineDto;
import com.hms.Appointment.entity.Medicine;
import com.hms.Appointment.repository.MedicineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineServiceImpl implements MedicineService{
    private final MedicineRepository medicineRepository;

    @Override
    public Long saveMedicine(MedicineDto request) {

        return medicineRepository.save(request.toEntity()).getId();
    }

    @Override
    public List<MedicineDto> saveAllMedicines(List<MedicineDto> requestList) {
       return ((List<Medicine>) medicineRepository.saveAll(
               requestList.stream().map(MedicineDto::toEntity)
                       .toList())).stream().map(Medicine::toDto).toList();


    }

    @Override
    public List<MedicineDto> getAllMedicinesByPrescriptionId(Long prescriptionId) {
        return ((List<Medicine>) medicineRepository.findAllByPrescription_Id(prescriptionId))
                .stream().map(Medicine::toDto).toList();
    }
}
