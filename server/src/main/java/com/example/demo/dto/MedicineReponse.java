package com.example.demo.dto;

public record MedicineReponse(Long id,
        String name,
        String category,
        String unit,
        Double price,
        Integer quantity,
        String brand,
        String description) {
}
