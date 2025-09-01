package com.hms.PharmacyMS.service;

import com.hms.PharmacyMS.dto.MedicineDto;
import com.hms.PharmacyMS.entity.Medicine;
import com.hms.PharmacyMS.exception.HmsException;
import com.hms.PharmacyMS.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService{
    private final MedicineRepository medicineRepository;
    @Override
    public Long addMedicine(MedicineDto medicineDto) throws HmsException {
        Optional<Medicine> optional=medicineRepository
                .findByNameIgnoreCaseAndDosageIgnoreCase(medicineDto.getName(),medicineDto.getDosage());
        if(optional.isPresent()){
            throw new HmsException("MEDICINE_ALREADY_EXIST");
        }
        medicineDto.setCreateAt((LocalDateTime.now()));
        return medicineRepository.save(medicineDto.toEntity()).getId();
    }

    @Override
    public MedicineDto getMedicineById(Long id) throws HmsException {
        return medicineRepository.findById(id).orElseThrow(()->new HmsException("MEDICINE_NOT_FOUND")).toDto();
    }

    @Override
    public void updateMedicine(MedicineDto medicineDto) throws HmsException {
        Medicine existingMedicine=medicineRepository
                .findById(medicineDto.getId())
                .orElseThrow(()->new HmsException("MEDICINE_NOT_FOUND"));
        if(!medicineDto.getName().equalsIgnoreCase(existingMedicine.getDosage())){
            Optional<Medicine> optional=
                    medicineRepository.findByNameIgnoreCaseAndDosageIgnoreCase(
                    medicineDto.getName(),medicineDto.getDosage()
            );
            if(optional.isPresent()){
                throw new HmsException("MEDICINE_ALREADY_EXIST");
            }
        }
        existingMedicine.setName(medicineDto.getName());
        existingMedicine.setDosage(medicineDto.getDosage());
        existingMedicine.setCategory(medicineDto.getCategory());
        existingMedicine.setType(medicineDto.getType());
        existingMedicine.setManufacturer(medicineDto.getManufacturer());
        existingMedicine.setUnitPrice(medicineDto.getUnitPrice());
        existingMedicine.setCreateAt(medicineDto.getCreateAt());
        medicineRepository.save(existingMedicine);
    }

    @Override
    public void deleteMedicine(Long id) throws HmsException {

    }

    @Override
    public List<MedicineDto> getAllMedicines() throws HmsException {
        return ((List<Medicine>) medicineRepository.findAll()).stream().map(Medicine::toDto).toList();
    }
}
