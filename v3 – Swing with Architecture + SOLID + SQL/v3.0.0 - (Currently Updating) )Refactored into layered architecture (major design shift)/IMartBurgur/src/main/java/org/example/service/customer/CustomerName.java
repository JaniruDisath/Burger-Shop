package org.example.service.customer;

public class CustomerName {

    public String getValidCustomerName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Please Enter Customer Name");
        }
        return name;
    }
}
