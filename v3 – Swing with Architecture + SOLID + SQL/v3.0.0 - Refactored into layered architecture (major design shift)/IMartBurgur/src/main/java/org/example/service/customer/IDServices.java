package org.example.service.customer;

public class IDServices {

    public long getCustomerIDValidated(String customerIDText) {

        if (customerIDText == null || customerIDText.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is empty");
        }

        String trimmed = customerIDText.trim();
        if (trimmed.charAt(0) != '0') {
            throw new IllegalArgumentException("Customer ID must start with '0'");
        }
        if (trimmed.length() != 10) {
            throw new IllegalArgumentException("Customer ID must be 10 digits");
        }
        try {
            return Long.parseLong(trimmed);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Customer ID must be a number");
        }
    }

    public long getValidatedCustomerID(String idString) {
            return getCustomerIDValidated(idString);
    }
}
