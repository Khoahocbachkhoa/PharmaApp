package com.example.demo.service;

import com.example.demo.dto.InvoiceCreateRequest;
import com.example.demo.dto.InvoiceDetailRequest;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.InvoiceDetail;
import com.example.demo.entity.Medicine;
import com.example.demo.exception.InvoiceException;
import com.example.demo.repository.InvoiceDetailRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.MedicineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    private final InvoiceDetailRepository invoiceDetailRepository;

    private final MedicineRepository medicineRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            InvoiceDetailRepository invoiceDetailRepository,
            MedicineRepository medicineRepository
    ) {
        this.invoiceDetailRepository = invoiceDetailRepository;
        this.invoiceRepository = invoiceRepository;
        this.medicineRepository = medicineRepository;
    }

    @Transactional
    public Invoice createInvoice(InvoiceCreateRequest request) {
        Invoice newInvoice = new Invoice();
        newInvoice.setCustomerId(request.getCustomerId());
        newInvoice.setDateCreated(LocalDateTime.now());
        newInvoice.setTotalAmount(0.0);

        newInvoice = invoiceRepository.save(newInvoice);

        double grandTotal = 0;
        List<InvoiceDetail> detailsToSave = new ArrayList<>();

        for (InvoiceDetailRequest item : request.getDetails()) {
            long medId = item.getMedicineId();
            int qty = item.getQuantity();

            Medicine medicine = medicineRepository.findById(medId)
                    .orElseThrow(() -> new InvoiceException("Thuốc ID " + medId + " không tồn tại"));

            if (medicine.getQuantity() < qty) {
                throw new RuntimeException("Thuốc '" + medicine.getName() + "' không đủ tồn kho (Trong kho: "
                        + medicine.getQuantity() + ")");
            }

            medicine.setQuantity(medicine.getQuantity() - qty);
            medicineRepository.save(medicine);

            double currentPrice = medicine.getPrice();
            double subTotal = currentPrice * qty;
            grandTotal += subTotal;

            InvoiceDetail newDetail = new InvoiceDetail();
            newDetail.setInvoiceId(newInvoice.getInvoiceId());
            newDetail.setMedicineId(medId);
            newDetail.setQuantity(qty);
            newDetail.setUnitPrice(currentPrice);
            newDetail.setSubTotal(subTotal);

            detailsToSave.add(newDetail);
        }

        invoiceDetailRepository.saveAll(detailsToSave);

        newInvoice.setTotalAmount(grandTotal);
        return invoiceRepository.save(newInvoice);
    }
}
