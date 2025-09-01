package org.example.model;

public class OrderDetails {

    public OrderDetails() {
    }

    public OrderDetails(String customerID, String customerName, String burgerNumber, String totalPrice, String orderStatus) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.burgerNumber = burgerNumber;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBurgerNumber() {
        return burgerNumber;
    }

    public void setBurgerNumber(String burgerNumber) {
        this.burgerNumber = burgerNumber;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    private String customerID;
    private String customerName;
    private String burgerNumber;
    private String totalPrice;
    private String orderStatus;

}
