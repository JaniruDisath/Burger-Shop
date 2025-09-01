package org.example.service.order;

import org.example.config.FoodMenu;
import org.example.controller.SearchOrderController;
import org.example.elements.InCodeTools;
import org.example.model.OrderDetails;
import org.example.repository.OurDataBase;

import java.awt.*;

public class FindOrder {

    private SearchOrderController controller;

    public FindOrder(SearchOrderController controller) {
        this.controller = controller;
    }

    public void loadOrderDetails(String orderIDEntered) {
        controller.setValuesOnView(getOrderDetails(orderIDEntered));
    }

    public OrderDetails getOrderDetails(String orderIDEntered) {
        try {
            return findOrderDetails(orderIDEntered);
        } catch (IllegalArgumentException e) {
            controller.getWarningTextLabel().setText(e.getMessage());
        }
        return new OrderDetails("", "", "", "", "");
    }


    public OrderDetails findOrderDetails(String orderIDEntered) {
        String orderIDWhat = orderIDEntered.trim().toUpperCase();
        System.out.println("Start :" + orderIDWhat);

        int id = 2;
        int gotit = 0;
        int profid = 0;

        if (Boolean.TRUE.equals(isIDEmpty(orderIDEntered))) {
            throw new IllegalArgumentException("Empty Field or Empty Database");
        }
        if (Boolean.TRUE.equals(isFirstNumberValid(orderIDWhat))) {
            throw new IllegalArgumentException("Order ID must start with 'B'");
        }

        if (orderIDWhat.matches("B-D0{4}U")) {
//            developerOptions.setVisible(true);
            System.out.println("Login : ADMIN");
            controller.serWarningText("Please Enter Developer PassCode");
            return new OrderDetails("Master Developer", "Login \\_", "", "", "Developer Access");
//            setDeveloperOptions();

        }

        if (orderIDWhat.length() < 5) {
            throw new IllegalArgumentException("ID length is shorter");
        }

        if (orderIDWhat.length() > 5) {
            throw new IllegalArgumentException("ID length is longer");
        }

        if (orderIDWhat.matches("B0{4}")) {
            System.out.println("Loop :" + orderIDWhat);
            controller.serWarningText("Order ID Reset Complete");
            OurDataBase.devOptions = 0;
            return new OrderDetails("" + 0, orderIDWhat, "" + 0, "" + (FoodMenu.BURGERPRICE * 0), "RESET");
        }
        if (!orderIDWhat.matches("B\\d{4}")) {
            throw new IllegalArgumentException("Invalid format. Use B0000");
        }

        controller.setWarningTextColor(new Color(0, 255, 0));
        controller.serWarningText("Order ID Confirmed");
        char char2 = orderIDWhat.charAt(1);
        char char3 = orderIDWhat.charAt(2);
        char char4 = orderIDWhat.charAt(3);
        char char5 = orderIDWhat.charAt(4);
        String checkString = "" + char2 + char3 + char4 + (char5);

        int checkInt = (Integer.parseInt(checkString) - 1);
        if (!(checkInt <= OurDataBase.SHARED_DB.getLatestOrder() - 1)){
            throw new IllegalArgumentException();
        }

        if (checkInt <= OurDataBase.SHARED_DB.getLatestOrder() - 1) {
            System.out.println(checkInt);
            if (OurDataBase.SHARED_DB.getOrderIDStringSOD(checkInt).equals(orderIDWhat)) {
                id = checkInt;
                gotit = 1;
                System.out.println("Found You :" + orderIDWhat);
            } else {
                for (int i = 1; i <= OurDataBase.SHARED_DB.getLatestOrder() - 1; i++) {

                    System.out.println("Comparing with DB ID: " + OurDataBase.SHARED_DB.getOrderStatusSOD(i));
                    if (OurDataBase.SHARED_DB.getOrderIDStringSOD(i).equals(orderIDWhat)) {
                        id = i;
                        gotit = 1;
                        break;
                    }
                }
            }
        }
        if (gotit != 1) {
            controller.setWarningTextColor(new Color(255, 0, 0));
            throw new IllegalArgumentException("Entered Order Does not Exists");

        }
        String orderStatusDetails = "";

        switch (OurDataBase.SHARED_DB.getOrderStatusSOD(id)) {
            case 0:
                orderStatusDetails = "PREPARING";
                break;
            case 1:
                orderStatusDetails = "DELIVERED";
                break;
            case 2:
                orderStatusDetails = "CANCEL";
                break;
        }

        for (int i = 0; i < OurDataBase.SHARED_DB.getLatestProfile(); i++) {
            if (OurDataBase.SHARED_DB.getProfCustIDSOD(i) == OurDataBase.SHARED_DB.getOrderCustIDSOD(id)) {
                profid = i;
            }
        }
        InCodeTools.printExtra(3, 2);
        InCodeTools.printExtra(3, 3);
        InCodeTools.printExtra(92, 1);
        System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "", "Order ID", "Customer ID", "Name", "Quantity", "Order Value", "Order Statue");
        InCodeTools.printExtra(3, 3);
        InCodeTools.printExtra(92, 1);
        System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "", OurDataBase.SHARED_DB.getOrderIDStringSOD(id), "0" + OurDataBase.SHARED_DB.getProfCustIDSOD(profid), OurDataBase.SHARED_DB.getCustNameSOD(profid), OurDataBase.SHARED_DB.getBurgerNumberSOD(id), (FoodMenu.BURGERPRICE * OurDataBase.SHARED_DB.getBurgerNumberSOD(id)), orderStatusDetails);
        InCodeTools.printExtra(3, 3);
        InCodeTools.printExtra(92, 1);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCustomerID("0" + OurDataBase.SHARED_DB.getProfCustIDSOD(profid));
        orderDetails.setCustomerName(OurDataBase.SHARED_DB.getCustNameSOD(profid));
        orderDetails.setBurgerNumber("" + OurDataBase.SHARED_DB.getBurgerNumberSOD(id));
        orderDetails.setTotalPrice("" + (FoodMenu.BURGERPRICE * OurDataBase.SHARED_DB.getBurgerNumberSOD(id)));
        orderDetails.setOrderStatus(orderStatusDetails);
        return orderDetails;


    }

    //Logics
    public Boolean isIDEmpty(String orderIDEntered) {
        return orderIDEntered.isEmpty();
    }

    public Boolean isFirstNumberValid(String orderIDEntered) {
        return orderIDEntered.charAt(0) == 'B';
    }

    //


}
