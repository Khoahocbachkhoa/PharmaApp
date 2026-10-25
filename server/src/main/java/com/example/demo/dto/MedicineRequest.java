package com.example.demo.dto;

public class MedicineRequest {
    private String name;
    private String category;
    private String unit;
    private Double price;
    private Integer supplierId;
    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description;}
}
