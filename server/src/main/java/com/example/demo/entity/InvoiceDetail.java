package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoicedetailid")
    private Long invoiceDetailId;

    @Column(name = "invoiceid")
    private Long invoiceId;

    @Column(name = "medicineid")
    private Long medicineId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unitprice", nullable = false)
    private Double unitPrice;

    @Column(name = "subtotal", nullable = false)
    private Double subTotal;

    public Long getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setInvoiceDetailId(Long invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
