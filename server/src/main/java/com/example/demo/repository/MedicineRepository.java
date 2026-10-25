package com.example.demo.repository;

import com.example.demo.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineRepository
    extends JpaRepository<Medicine, Long> {

    @Query("SELECT m FROM Medicine m JOIN m.supplier s WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Medicine> searchMedicinesByName(@Param("query") String query);
}
