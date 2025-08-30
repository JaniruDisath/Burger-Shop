package org.example.controller;

import org.example.view.*;

public class SearchMenuController {

    private SearchMenu view;
    public SearchMenuController(SearchMenu view){
        this.view = view;
    }

    public void init(){
        view.onBestCustomer(e -> {
            view.close();
            new SearchBestCustomer().openSearchBestCustomer();
        });
        view.onSearchOrder(e -> {
            view.close();
            new SearchOrderDetails().openSearchOrderDetials();
        });
        view.onSearchCustomer(e -> {
            view.close();
            new SearchCustomerDetails().openSearchCustomerDetials();
        });
        view.onBackToHomePage(e -> {
            view.close();
            new MainWindow().openMainWindow();
        });
    }
}
