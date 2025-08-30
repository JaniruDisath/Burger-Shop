package org.example.service.customer;

import org.example.repository.OurDataBase;

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

    public long getCustomerIDIgnoreExceptions(long customerID){
        try {
            return customerID;
        } catch (IllegalArgumentException ignored) {
            //Ignoring the exceptions
        }
        return -1;
    }

    public int findCustomerIDPosition(long customerID){
        if (customerID==-1)return -1;
        for (int i = 0; i < OurDataBase.SHARED_DB.getLatestProfile(); i++) {
            if (OurDataBase.SHARED_DB.getProfCustIDSOD(i) == customerID) {
                return i;
            }
        }
        return -1;
    }
}
