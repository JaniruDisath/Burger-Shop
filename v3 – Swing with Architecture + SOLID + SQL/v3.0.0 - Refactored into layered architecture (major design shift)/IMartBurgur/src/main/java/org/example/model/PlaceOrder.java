package org.example.model;

public class PlaceOrder {

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBurgerNumber() {
        return burgerNumber;
    }

    public void setBurgerNumber(int burgerNumber) {
        this.burgerNumber = burgerNumber;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }



    public PlaceOrder(long customerID, String name, int burgerNumber, String orderID) {
        this.customerID = customerID;
        this.name = name;
        this.burgerNumber = burgerNumber;
        this.orderID = orderID;
    }

    public PlaceOrder() {
    }

    private long customerID;
    private String name;
    private int burgerNumber;
    private String orderID;

}
