package org.example.service.order;

import org.example.config.FoodMenu;
import org.example.controller.PlaceOrderController;
import org.example.model.PlaceOrder;
import org.example.repository.Orders;
import org.example.repository.OurDataBase;
import org.example.service.burger.BurgerNumber;
import org.example.service.customer.CustomerName;
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

    //Customer ID - getValues with the exceptions
    public long getCustomerID() {
        return new IDServices().getValidatedCustomerID(controller.getCustomerIDString());
    }

    //Burger Number Validation with exception
    public int getValidatedBurgerNumber() {
        return new BurgerNumber().getValidBurgerNumber(controller.getBurgerNumberString());
    }

    //DatabaseFunctions
    public void addOrderToDatabase() {

        try {
            long customerID = getCustomerID();
            String name = new CustomerName().getValidCustomerName(controller.getCustomerName());
            int burgerNumber = getValidatedBurgerNumber();
            String orderID = new GenerateOrderID().generateId();

            placeNewOrder(new PlaceOrder(customerID, name, burgerNumber, orderID));

            controller.sendOptionPaneMessage("Order Added Successfully");
            controller.closePlaceOrderWindow();

        } catch (IllegalArgumentException e) {
            controller.sendOptionPaneMessage(e.getMessage());
        }
        //TODO - Move these to repository on the development on that class
    }

    public void placeNewOrder(PlaceOrder placeOrder) {
        new Orders().placeNewOrder(placeOrder);
    }

    public void findCustomer() {
        long customerID = getCustomerIDIgnoreExceptions();
        int profilePosition = new IDServices().findCustomerIDPosition(customerID);
        if (profilePosition != -1) {
            setFoundCustomer(profilePosition);
        }
    }

    public long getCustomerIDIgnoreExceptions(){
        try {
            return getCustomerID();
        } catch (IllegalArgumentException ignored) {
            //Ignoring the exceptions
        }
        return -1;
    }

    public void setFoundCustomer(int profilePosition) {
        controller.setName(OurDataBase.SHARED_DB.getCustNameSOD(profilePosition));
        controller.setNameFieldEditable(false);
    }
}
