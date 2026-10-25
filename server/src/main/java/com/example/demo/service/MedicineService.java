package com.example.demo.service;

import com.example.demo.dto.MedicineReponse;
import com.example.demo.dto.MedicineRequest;
import com.example.demo.entity.Medicine;
import com.example.demo.exception.MedicineException;
import com.example.demo.repository.MedicineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository repository) {
        medicineRepository = repository;
    }

    public List<MedicineReponse> searchMedicines(String q) {
        if (q == null || q.trim().isEmpty()) {
            return List.of();
        }

        List<Medicine> medicines = medicineRepository.searchMedicinesByName(q.trim());

        return medicines.stream().map(med -> new MedicineReponse(
                med.getMedicineId(),
                med.getName(),
                med.getCategory(),
                med.getUnit(),
                med.getPrice(),
                med.getQuantity(),
                med.getSupplier().getName(),
                med.getDescription()
        )).toList();
    }

    @Transactional
    public Medicine addNewMedicine(MedicineRequest request) {
        if (request.getName() == null || request.getPrice() == null || request.getSupplierId() == null) {
            throw new MedicineException("Vui lòng nhập đủ thông tin!");
        }

        Medicine newMed = new Medicine();
        newMed.setName(request.getName());
        newMed.setCategory(request.getCategory());
        newMed.setUnit(request.getUnit());
        newMed.setPrice(request.getPrice());
        newMed.setDescription(request.getDescription());
        newMed.setQuantity(0);

        return medicineRepository.save(newMed);
    }
}
