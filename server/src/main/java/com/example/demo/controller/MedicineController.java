package com.example.demo.controller;

import com.example.demo.dto.MedicineReponse;
import com.example.demo.dto.MedicineRequest;
import com.example.demo.entity.Medicine;
import com.example.demo.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMedicines(
            @RequestParam(name = "q", defaultValue = "") String q
    ) {
        List<MedicineReponse> results = medicineService.searchMedicines(q);
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<?> addNewMedicine(
            @RequestBody MedicineRequest request
    ) {
        Map<String, Object> response = new HashMap<>();

        Medicine medicine = medicineService.addNewMedicine(request);

        response.put("msg", "Thêm thuốc mới thành công!");
        response.put("id", medicine.getMedicineId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
