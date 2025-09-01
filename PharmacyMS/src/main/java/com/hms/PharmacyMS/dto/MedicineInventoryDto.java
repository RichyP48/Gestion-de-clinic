package com.hms.PharmacyMS.dto;

import com.hms.PharmacyMS.entity.Medicine;
import com.hms.PharmacyMS.entity.MedicineInventory;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInventoryDto {
    private Long id;
    private Long medicineId;
    private String batchNo;
    private Integer quantity;
    private LocalDate expiryDate;
    private LocalDate addedDate;

    public MedicineInventory toentity(){
        return new MedicineInventory(id,
                new Medicine(medicineId),
                batchNo,
                quantity, expiryDate, addedDate);
    }
}
