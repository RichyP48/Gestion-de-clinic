package com.hms.PharmacyMS.api;

import com.hms.PharmacyMS.dto.MedicineDto;
import com.hms.PharmacyMS.dto.ResponseDto;
import com.hms.PharmacyMS.exception.HmsException;
import com.hms.PharmacyMS.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pharmacy/medicines")
@RequiredArgsConstructor
public class MedicineApi {
    private final MedicineService medicineService;

    @PostMapping("/add")
    public ResponseEntity<Long> addMedicine(@RequestBody MedicineDto medicineDto) throws HmsException{
        return new ResponseEntity<>(medicineService.addMedicine(medicineDto), HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<MedicineDto> getMedicineById(@PathVariable Long id) throws HmsException{
        return new ResponseEntity<>(medicineService.getMedicineById(id), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateMedicine(@RequestBody MedicineDto medicineDto) throws HmsException{
        medicineService.updateMedicine(medicineDto);
        return new ResponseEntity<>(new ResponseDto("Medicine Updated"),HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<MedicineDto>> getAllMedicine() throws HmsException{
        return new ResponseEntity<>(medicineService.getAllMedicines(), HttpStatus.OK);
    }
}
