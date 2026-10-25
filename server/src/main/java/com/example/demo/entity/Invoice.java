package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceid")
    private Long invoiceId;

    @CreationTimestamp
    @Column(name = "datecreated", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "totalamount")
    private Double totalAmount = 0.0;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
