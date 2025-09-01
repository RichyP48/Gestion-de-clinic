package com.hms.PharmacyMS.repository;

import com.hms.PharmacyMS.entity.Medicine;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {
    Optional<Medicine> findByNameIgnoreCaseAndDosageIgnoreCase(String name, String dosage);
}
