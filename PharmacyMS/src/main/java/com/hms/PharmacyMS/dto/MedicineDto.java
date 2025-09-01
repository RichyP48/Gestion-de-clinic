package com.hms.PharmacyMS.dto;

import com.hms.PharmacyMS.entity.Medicine;
import com.hms.PharmacyMS.entity.MedicineCategory;
import com.hms.PharmacyMS.entity.MedicineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {
    private Long id;
    private String name;
    private String dosage;
    private MedicineCategory category;
    private MedicineType type;
    private String manufacturer;
    private Integer unitPrice;
    private LocalDateTime createAt;

    public Medicine toEntity(){
        return new Medicine(id,
                name, dosage,
                category, type, manufacturer, unitPrice, createAt);
    }
}
