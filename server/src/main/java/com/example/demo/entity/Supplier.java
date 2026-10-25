package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid")
    private Long supplierId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "phone", length = 50)
    private String phone;

    public Long getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }
}
