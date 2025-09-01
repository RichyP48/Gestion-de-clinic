package com.hms.profile.dto;

import com.hms.profile.entity.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorDto {
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
    private String phone;
    private String address;
    @Column(unique = true)
    private String licenseNo;
    private String specialization;
    private String department;
    private Integer totalExp;

    public Doctor toEntity(){
       return new Doctor(this.id,
               this.name,
               this.email,
               this.dob,
               this.phone,
               this.address,
               this.licenseNo,
               this.specialization,
               this.department,
               this.totalExp);
    }
}
