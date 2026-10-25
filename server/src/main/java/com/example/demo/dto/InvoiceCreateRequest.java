package com.example.demo.dto;

import java.util.List;

public class InvoiceCreateRequest {
    private Integer customerId;
    private List<InvoiceDetailRequest> details;

    public Integer getCustomerId() {
        return customerId;
    }

    public List<InvoiceDetailRequest> getDetails() {
        return details;
    }
}
