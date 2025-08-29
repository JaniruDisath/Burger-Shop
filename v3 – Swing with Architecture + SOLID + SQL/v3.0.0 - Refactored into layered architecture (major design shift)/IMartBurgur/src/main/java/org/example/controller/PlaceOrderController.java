package org.example.controller;

import org.example.handler.PlaceOrderHandler;
import org.example.view.MainWindow;
import org.example.view.PlaceOrderWindow;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PlaceOrderController {

    private final PlaceOrderWindow view;
    private final PlaceOrderHandler handler;

    public PlaceOrderController(PlaceOrderWindow view) {
        this.view = view;
        this.handler = new PlaceOrderHandler(this);
    }

    public void init() {
        view.onBackToHomePage(e -> handler.handleBackToHomePage());
        view.onConfirmOrder(e -> handler.handleConfirmOrder());
        view.onBurgerNumberChange(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                setTotalValue();
            }

            public void removeUpdate(DocumentEvent e) {
                setTotalValue();
            }

            public void changedUpdate(DocumentEvent e) {
                setTotalValue();
            }
        });

        view.onCustomerIDChange(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setCustomerNameFound();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setCustomerNameFound();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setCustomerNameFound();
            }
        });

    }

    public void setCustomerNameFound() {
        setName("");
        setNameFieldEditable(true);
        handler.handleFindCustomerID();
    }

    public void closePlaceOrderWindow() {
        view.close();
        new MainWindow().openMainWindow();
    }

    //View Values Getters
    public String getCustomerIDString() {
        return view.getCustomerIDString();
    }

    public String getCustomerName() {
        return view.getCustomerName();
    }

    public String getBurgerNumberString() {
        return view.getBurgerNumberString();
    }

    //View Controllers
    public void sendOptionPaneMessage(String message) {
        view.sendOptionPaneMessage(message);
    }

    public void setTotalValue() {
        view.setTotalValue(handler.handleTotalValues(view.getEnteredBurgerNumberString()));
    }

    public void setName(String name) {
        view.setName(name);
    }

    public void setNameFieldEditable(boolean bool) {
        view.setNameFieldEditable(bool);
    }

}
