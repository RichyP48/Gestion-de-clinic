package com.hms.Appointment.dto;


import com.hms.Appointment.entity.Medicine;
import com.hms.Appointment.entity.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    private Long id;
    private String name;
    private  Long medicineId;

    private String dosage;
    private String frequency;
    private Integer duration; //in days
    private String route; //e.g oral, intravenous
    private String type; // e.g, tablet, syrup, injection
    private String instructions;
    private Long prescriptionId;

    public Medicine toEntity(){
        return new Medicine(
                id,
                name, medicineId, dosage, frequency, duration, route, type, instructions,
                new Prescription(prescriptionId)
        );
    }

}