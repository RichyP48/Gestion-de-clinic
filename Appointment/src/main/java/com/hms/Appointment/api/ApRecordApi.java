package com.hms.Appointment.api;

import com.hms.Appointment.dto.ApRecordDto;
import com.hms.Appointment.dto.PrescriptionDetails;
import com.hms.Appointment.dto.RecordDetails;
import com.hms.Appointment.entity.ApRecord;
import com.hms.Appointment.exception.HmsException;
import com.hms.Appointment.service.ApRecordService;
import com.hms.Appointment.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/appointment/report")
@Validated
@RequiredArgsConstructor
public class ApRecordApi {
    private final ApRecordService apRecordService;
    private final PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<Long> createAppointmentReport(@RequestBody ApRecordDto request) throws HmsException {
    return new ResponseEntity<>(apRecordService.createApRecord(request), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateAppointmentReport(@RequestBody ApRecordDto request) throws HmsException {
        apRecordService.updateApRecord(request);
        return new ResponseEntity<>("Appointment Report Updated.", HttpStatus.OK);
    }
    @GetMapping("/getByAppointmentId/{appointmentId}")
    public ResponseEntity<ApRecordDto> getAppointmentReportByAppointmentId(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordByAppointmentId(appointmentId), HttpStatus.OK);
    }
    @GetMapping("/getDetailsByAppointmentId/{appointmentId}")
    public ResponseEntity<ApRecordDto> getAppointmentReportDetailsByAppointmentId(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordDetailsByAppointmentId(appointmentId), HttpStatus.OK);
    }
    @GetMapping("/getById/{recordId}")
    public ResponseEntity<ApRecordDto> getAppointmentReportById(@PathVariable Long recordId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordById(recordId), HttpStatus.OK);
    }
    @GetMapping("/getRecordsByPatientId/{patientId}")
    public ResponseEntity<List<RecordDetails>> getRecordsByPatientId(@PathVariable Long patientId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getRecordsByPatientId(patientId), HttpStatus.OK);
    }
    @GetMapping("/isRecordExists/{appointmentId}")
    public ResponseEntity<Boolean> isRecordExists(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(apRecordService.isRecordExists(appointmentId), HttpStatus.OK);
    }
    @GetMapping("/getPrescriptionsByPatientId/{patientId}")
    public ResponseEntity<List<PrescriptionDetails>> getPrescriptionsByPatientId(@PathVariable Long patientId) throws HmsException {
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByPatientId(patientId), HttpStatus.OK);
    }


}
