package com.example.demo.controller;

import com.example.demo.dto.InvoiceCreateRequest;
import com.example.demo.entity.Invoice;
import com.example.demo.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(
            @RequestBody InvoiceCreateRequest request
    ) {
        Map<String, Object> response = new HashMap<>();

        Invoice savedInvoice = invoiceService.createInvoice(request);

        response.put("msg", "Thành công!");
        response.put("id", savedInvoice.getInvoiceId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}