package com.example.demo.service;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> getCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(c -> new CustomerResponse(
                        c.getCustomerId(),
                        c.getName(),
                        c.getPhone(),
                        c.getAddress()
                ))
                .toList();
    }

    public List<Customer> findCustomers(String q) {
        q = q.trim();

        return customerRepository.findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(q, q);
    }

    @Transactional
    public Customer addCustomer(CustomerRequest customerRequest) {
        if (customerRequest.getName() == null || customerRequest.getName().isBlank()) {
            throw new CustomerException("Tên khách hàng không hợp lệ");
        }

        Customer customer = new Customer();

        customer.setName(customerRequest.getName());
        customer.setPhone(customerRequest.getPhone());
        customer.setAddress(customerRequest.getAddress());

        return customerRepository.save(customer);
    }
}
