package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @Positive(message = "Total must be positive")
    private Double total;


    public Order () {}

    public Order (String customerName, LocalDate orderDate, String shippingAddress, Double total) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

