package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "import_receipts")
public class ImportReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receiptid")
    private Long receiptId;

    @Column(name = "supplierid")
    private Integer supplierId;

    @CreationTimestamp
    @Column(name = "dateimported", updatable = false)
    private LocalDateTime dateImported;

    @Column(name = "totalcost")
    private Double totalCost = 0.0;

    public Long getReceiptId() {
        return receiptId;
    }
}
