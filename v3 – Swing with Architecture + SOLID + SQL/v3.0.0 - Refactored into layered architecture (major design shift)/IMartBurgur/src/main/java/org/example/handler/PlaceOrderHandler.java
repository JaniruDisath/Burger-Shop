package org.example.handler;

import org.example.controller.PlaceOrderController;
import org.example.service.order.PlaceOrderServices;
import org.example.view.MainWindow;

public class PlaceOrderHandler {

    private final PlaceOrderController controller;
    private final PlaceOrderServices services;

    public PlaceOrderHandler(PlaceOrderController controller ){
        this.controller = controller;
        this.services=new PlaceOrderServices(controller);

    }

    public void handleBackToHomePage() {
        controller.closePlaceOrderWindow();

    }

    public void handleConfirmOrder() {
        services.addOrderToDatabase();
    }

    public void handleFindCustomerID(){
        services.findCustomerID();
    }

    public double handleTotalValues(String stringValue){
        return services.getTotalValue(stringValue);
    }

}
