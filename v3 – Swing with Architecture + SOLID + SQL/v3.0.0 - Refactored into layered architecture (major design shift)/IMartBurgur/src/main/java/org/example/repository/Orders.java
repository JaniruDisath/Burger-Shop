package org.example.repository;

import org.example.config.FoodMenu;
import org.example.config.OrderStatus;
import org.example.model.PlaceOrder;

public class Orders {

    private BurgerShopDatabase database = OurDataBase.SHARED_DB;

    public void placeNewOrder(PlaceOrder placeOrder) {

        long customerID = placeOrder.getCustomerID();
        String name = placeOrder.getName();
        int burgerNumber = placeOrder.getBurgerNumber();
        String orderID = placeOrder.getOrderID();

        int gotIt = 0, profileID = 0;
        double totalBurgerPrice = burgerNumber * FoodMenu.BURGERPRICE;

        for (int i = 0; i < database.getLatestProfile(); i++) {
            if (database.getProfCustIDSOD(i) == customerID) {
                gotIt = 1;
                profileID = i;
                break;
            }
        }
        database.extendOrderArrays();
        int cOI = database.getLatestOrder() - 1;
        database.setBurgerNumberSOD(burgerNumber, cOI);
        database.setOrderIDStringSOD(orderID, cOI);
        database.setOrderStatusSOD(OrderStatus.PREPARING, cOI);
        database.setOrderCustIDSOD(customerID, cOI);
        switch (gotIt) {
            case 0:
                database.extendProfileArrays();
                int cPI = database.getLatestProfile() - 1;
                database.setProfCustIDSOD(customerID, cPI);
                database.setCustNameSOD(name, cPI);
                database.setCustTotalSOD(totalBurgerPrice, cPI);
                break;
            case 1:
                database.setCustTotalSOD(totalBurgerPrice, profileID);
                break;
        }
        System.out.println("New Order Added : " + customerID + " : " + name + " : " + burgerNumber + " : " + orderID);
    }

    public void addOrderToDatabase(){

    }
}
