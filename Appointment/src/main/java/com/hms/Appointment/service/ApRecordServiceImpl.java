package com.hms.Appointment.service;

import com.hms.Appointment.client.ProfileClient;
import com.hms.Appointment.dto.ApRecordDto;
import com.hms.Appointment.dto.DoctorName;
import com.hms.Appointment.dto.RecordDetails;
import com.hms.Appointment.entity.ApRecord;
import com.hms.Appointment.exception.HmsException;
import com.hms.Appointment.repository.ApRecordRepository;
import com.hms.Appointment.utility.StringListConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApRecordServiceImpl implements ApRecordService{
    private final ApRecordRepository apRecordRepository;
    private final PrescriptionService prescriptionService;
    private final ProfileClient profileClient;


    @Override
    public Long createApRecord(ApRecordDto request) throws HmsException {
        Optional<ApRecord> existingRecord=apRecordRepository.findByAppointment_Id(request.getAppointmentId());
        if(existingRecord.isPresent()){
            throw new HmsException("APPOINTMENT_RECORD_ALREADY_EXIST");
        }
        request.setCreateAt(LocalDateTime.now());
        Long id=apRecordRepository.save(request.toEntity()).getId();
        if(request.getPrescription()!=null){
            request.getPrescription().setAppointmentId(request.getAppointmentId());
            prescriptionService.savePrescription(request.getPrescription());
        }
        return id;
    }

    @Override
    public void updateApRecord(ApRecordDto request) throws HmsException {
        ApRecord existing=apRecordRepository
                .findById(request.getId())
                        .orElseThrow(()->new HmsException("APPOINTMENT_RECORD_NOT_FOUND"));
        existing.setNotes(request.getNotes());
        existing.setDiagnosis(request.getDiagnosis());
        existing.setFollowUpDate(request.getFollowUpDate());
        existing.setSymptoms(StringListConverter.convertListToString(request.getSymptoms()));
        existing.setTests(StringListConverter.convertListToString(request.getTests()));

        apRecordRepository.save(existing);
    }

    @Override
    public ApRecordDto getApRecordByAppointmentId(Long appointmentId) throws HmsException {
        return apRecordRepository.findByAppointment_Id(appointmentId)
                .orElseThrow(()->new HmsException("APPOINTMENT_RECORD_NOT_FOUND")).toDto();
    }

    @Override
    public ApRecordDto getApRecordDetailsByAppointmentId(Long appointmentId) throws HmsException {
        ApRecordDto record= apRecordRepository.findByAppointment_Id(appointmentId)
                .orElseThrow(()->new HmsException("APPOINTMENT_RECORD_NOT_FOUND")).toDto();
        record.setPrescription(prescriptionService.getPrescriptionByAppointmentId(appointmentId));
        return record;
    }

    @Override
    public ApRecordDto getApRecordById(Long recordId) throws HmsException {
        return apRecordRepository.findById(recordId).orElseThrow(()->new HmsException("APPOINTMENT_RECORD_NOT_FOUND")).toDto();
    }

    @Override
    public List<RecordDetails> getRecordsByPatientId(Long patientId) throws HmsException {
        List<ApRecord> records=apRecordRepository.findByPatientId(patientId);
        List<RecordDetails> recordDetails=records.stream()
                .map(ApRecord::toRecordDetails).toList();
        List<Long>doctorIds=recordDetails.stream()
                .map(RecordDetails::getDoctorId)
                .distinct().toList();
//        profileClient.getDoctorsById(doctorIds).forEach(doctor->{
//            recordDetails.stream()
//                    .filter(record->record.getDoctorId().equals(doctor.getId()))
//                    .forEach(record->record.setDoctorName(doctor.getName()));
//        });
        List<DoctorName> doctors=profileClient.getDoctorsById(doctorIds);
        Map<Long, String> doctorMap=doctors.stream()
                .collect(Collectors.toMap(DoctorName::getId, DoctorName::getName));
        recordDetails.forEach(record->{
            String doctorName=doctorMap.get(record.getDoctorId());
            if(doctorName !=null){
                record.setDoctorName(doctorName);
            }else {
                record.setDoctorName("Unknown Doctor");
            }
        });
        return recordDetails;
    }

    @Override
    public Boolean isRecordExists(Long appointmentId) throws HmsException {
        return apRecordRepository.existsByAppointment_Id(appointmentId);
    }
}
