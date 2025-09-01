package org.example.controller;

import org.example.config.FoodMenu;
import org.example.elements.BurgurTextLables;
import org.example.model.OrderDetails;
import org.example.service.order.FindOrder;
import org.example.view.SearchOrderDetails;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SearchOrderController {

    private final SearchOrderDetails view;
    private FindOrder service;

    public SearchOrderController(SearchOrderDetails view) {
        this.view = view;
        service = new FindOrder(this);
    }

    public void init() {
        view.onOrderIDChange(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                makeChangesWithTheIdChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                makeChangesWithTheIdChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                makeChangesWithTheIdChange();
            }
        });
    }

    //Getters
    public String getOrderIdEntered() {
        return view.getTextFieldOrderIDEntered().getText();
    }

    public BurgurTextLables getWarningTextLabel() {
        return view.getWarningTextLabel();
    }

    public void getTextFieldDevOptions(String string) {
        view.getTextFieldDevOptions().setText(string);
    }


    //Setters
    public void setDefaultValuesOnView() {
        setValuesOnView(new OrderDetails("" + 0, "B0000", "" + 0, "" + (FoodMenu.BURGERPRICE * 0), ""));
    }

    public void setValuesOnView(OrderDetails orderDetails) {
        view.setValuesOnView(orderDetails);
    }

    //Warning Text
    public void serWarningText(String string){
        getWarningTextLabel().setText(string);
    }

    public void setWarningTextColor(Color color){
        getWarningTextLabel().setForeground(color);
    }


    //
    public void makeChangesWithTheIdChange() {
        setDefaultValuesOnView();
//        view.getDeveloperOptionsField().setVisible(false);
        getWarningTextLabel().setForeground(new Color(255, 0, 0));
        service.loadOrderDetails(getOrderIdEntered());
    }

}
