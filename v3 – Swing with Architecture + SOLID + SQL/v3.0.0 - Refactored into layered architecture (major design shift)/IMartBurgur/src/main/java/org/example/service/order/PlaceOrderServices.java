package org.example.service.order;

import org.example.config.FoodMenu;
import org.example.controller.PlaceOrderController;
import org.example.model.PlaceOrder;
import org.example.repository.Orders;
import org.example.repository.OurDataBase;
import org.example.service.customer.IDServices;

public class PlaceOrderServices {

    private final PlaceOrderController controller;

    public PlaceOrderServices(PlaceOrderController controller) {
        this.controller = controller;
    }

    //Calculations
    public double getTotalValue(String burgerNumberString) {
        try {
            return Integer.parseInt(burgerNumberString) * FoodMenu.BURGERPRICE;
        } catch (NumberFormatException e) {
            return 0.00;
        }
    }

    //Validations
    //Customer ID - getValues
    public long getCustomerID() {
            return new IDServices().getValidatedCustomerID(controller.getCustomerIDString());
    }

    //Customer Name Validation
    public String getValidCustomerName() {
        String name = controller.getCustomerName();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Please Enter Customer Name");
        }
        return name;
    }

    //Burger Number Validation
    public int getValidBurgerNumber(String burgerNumberString) {

        if (burgerNumberString.isEmpty()) {
            throw new IllegalArgumentException("Please Enter Burger Number");
        }

        int burgerNumber = 0;

        try {
            burgerNumber = Integer.parseInt(burgerNumberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number for Burger Quantity");
        }

        if (burgerNumber <= 0) {
            throw new IllegalArgumentException("Please enter a positive quantity");
        }

        return burgerNumber;
    }

    public int getValidatedBurgerNumber() {
            return getValidBurgerNumber(controller.getBurgerNumberString());
    }


    //DatabaseFunctions
    public void addOrderToDatabase() {

        try {
            long customerID = getCustomerID();
            String name = getValidCustomerName();
            int burgerNumber = getValidatedBurgerNumber();
            String orderID = new GenerateOrderID().generateId();

            placeNewOrder(new PlaceOrder(customerID,name,burgerNumber, orderID));

            controller.sendOptionPaneMessage("Order Added Successfully");
            controller.closePlaceOrderWindow();

        } catch (IllegalArgumentException e) {
            controller.sendOptionPaneMessage(e.getMessage());
        }
    //TODO - Move these to repository on the development on that class
    }
    public void placeNewOrder(PlaceOrder placeOrder){
        new Orders().placeNewOrder(placeOrder);
    }

    public void findCustomerID() {
        long customerID = 0;
        try {
            customerID = getCustomerID();
        } catch (IllegalArgumentException ignored) {

        }

        int prof = -1, gotit = 0;

        for (int i = 0; i < OurDataBase.SHARED_DB.getLatestProfile(); i++) {
            if (OurDataBase.SHARED_DB.getProfCustIDSOD(i) == customerID) {
                gotit = 1;
                prof = i;
                break;
            }
        }
        if (gotit == 1) {
            controller.setName(OurDataBase.SHARED_DB.getCustNameSOD(prof));
            controller.setNameFieldEditable(false);
        }
        //TODO - Simplify this method using Solid
    }


}
