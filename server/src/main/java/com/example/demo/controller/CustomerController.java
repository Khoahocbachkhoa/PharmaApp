package com.example.demo.controller;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomers(
            @RequestParam(required = false, defaultValue = "") String q
    ) {
        if (q.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        List<Customer> customers = customerService.findCustomers(q);

        List<CustomerResponse> data = customers.stream()
                .map(c -> new CustomerResponse(
                        c.getCustomerId(),
                        c.getName(),
                        c.getPhone(),
                        c.getAddress()
                ))
                .toList();

        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(
        @RequestBody CustomerRequest request
    ) {
        Customer customer = customerService.addCustomer(request);

        Map<String, Object> response = new HashMap<>();
        response.put("msg", "Thêm khách hàng thành công");
        response.put("id", customer.getCustomerId());

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(response);
    }
}
