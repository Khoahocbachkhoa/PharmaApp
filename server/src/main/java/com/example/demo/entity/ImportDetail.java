package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_details")
public class ImportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importdetailid")
    private Long importDetailId;

    @Column(name = "receiptid")
    private Integer receiptId;

    @Column(name = "medicineid")
    private Integer medicineId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "importprice", nullable = false)
    private Double importPrice;

    public Long getImportDetailId() {
        return importDetailId;
    }
}
