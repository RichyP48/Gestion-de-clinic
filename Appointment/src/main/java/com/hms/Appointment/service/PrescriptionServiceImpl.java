package com.hms.Appointment.service;

import com.hms.Appointment.client.ProfileClient;
import com.hms.Appointment.dto.DoctorName;
import com.hms.Appointment.dto.PrescriptionDetails;
import com.hms.Appointment.dto.PrescriptionDto;
import com.hms.Appointment.entity.Prescription;
import com.hms.Appointment.exception.HmsException;
import com.hms.Appointment.repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineService medicineService;
    private final ProfileClient profileClient;

    @Override
    public Long savePrescription(PrescriptionDto request) {
        request.setPrescriptionDate(LocalDate.now());
        Long prescriptionId=prescriptionRepository.save(request.toEntity()).getId();
        request.getMedicines().forEach(medicine -> {
            medicine.setPrescriptionId(prescriptionId);
        });
      medicineService.saveAllMedicines(request.getMedicines());
        return prescriptionId;
    }

    @Override
    public PrescriptionDto getPrescriptionByAppointmentId(Long appointmentId) throws HmsException {
        PrescriptionDto prescriptionDto=prescriptionRepository.findByAppointment_Id(appointmentId)
                .orElseThrow(()->new HmsException("PRESCRIPTION_NOT_FOUND")).toDto();

        prescriptionDto.setMedicines(medicineService.getAllMedicinesByPrescriptionId(prescriptionDto.getId()));

        return prescriptionDto;
    }

    @Override
    public PrescriptionDto getPrescriptionById(Long prescriptionId) throws HmsException {
        PrescriptionDto dto=prescriptionRepository.findById(prescriptionId).orElseThrow(()->new HmsException("PRESCRIPTION_NOT_FOUND")).toDto();
        dto.setMedicines(medicineService.getAllMedicinesByPrescriptionId(dto.getId()));
        return dto;
    }

    @Override
    public List<PrescriptionDetails> getPrescriptionsByPatientId(Long patientId) throws HmsException {
       List<Prescription> prescriptions=prescriptionRepository.findAllByPatientId(patientId);
       List<PrescriptionDetails> prescriptionDetails=prescriptions.stream()
               .map(Prescription::toDetails)
               .toList();
       prescriptionDetails.forEach(details->{
           details.setMedicines(medicineService.getAllMedicinesByPrescriptionId(details.getId()));
       });
       List<Long> doctorIds=prescriptionDetails.stream()
               .map(PrescriptionDetails::getDoctorId)
               .distinct()
               .toList();
       List<DoctorName> doctorNames=profileClient.getDoctorsById(doctorIds);
        Map<Long, String> doctorMap=doctorNames.stream()
                .collect(Collectors.toMap(DoctorName::getId, DoctorName::getName));
       prescriptionDetails.forEach(details->{
           String doctorName=doctorMap.get(details.getDoctorId());
           if(doctorName !=null){
               details.setDoctorName(doctorName);
           }else{
               details.setDoctorName("Unknown Doctor");
           }

       });
       return prescriptionDetails;
    }
}
